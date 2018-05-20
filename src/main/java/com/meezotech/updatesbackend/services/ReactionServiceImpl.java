package com.meezotech.updatesbackend.services;

import com.meezotech.updatesbackend.api.v1.mapper.ReactionMapper;
import com.meezotech.updatesbackend.api.v1.model.ReactionDTO;
import com.meezotech.updatesbackend.api.v1.model.ReactionListDTO;
import com.meezotech.updatesbackend.domain.Post;
import com.meezotech.updatesbackend.domain.Reaction;
import com.meezotech.updatesbackend.domain.User;
import com.meezotech.updatesbackend.notifications.NotificationUtility;
import com.meezotech.updatesbackend.repositories.PostRepository;
import com.meezotech.updatesbackend.repositories.ReactionRepository;
import com.meezotech.updatesbackend.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ReactionServiceImpl implements ReactionService {

    private ReactionRepository reactionRepository;
    private ReactionMapper reactionMapper;
    private PostRepository postRepository;
    private NotificationUtility notificationUtility;
    private UserRepository userRepository;

    public ReactionServiceImpl(ReactionRepository reactionRepository,
                               ReactionMapper reactionMapper, PostRepository postRepository,
                               NotificationUtility notificationUtility, UserRepository userRepository) {
        this.reactionRepository = reactionRepository;
        this.reactionMapper = reactionMapper;
        this.postRepository = postRepository;
        this.notificationUtility = notificationUtility;
        this.userRepository = userRepository;
    }

    @Override
    public ReactionDTO createReaction(ReactionDTO reactionDTO) {
        Reaction reaction = reactionRepository.save(
                reactionMapper.reactionDtoToReaction(reactionDTO));
        Post post = postRepository.findOne(reactionDTO.getPostId());
        User user = userRepository.findOne(reactionDTO.getUserId());
        reaction.setUser(user);
        User user1 = userRepository.findOne(post.getUser().getId());
        if (!reaction.getUser().getId().equals(user1.getId()))
            notificationUtility.sendReactionNotification(reaction,
                    post.getUser().getId(), user1.getNotificationToken());
        return reactionMapper.reactionToReactionDto(reaction);
    }

    @Override
    public void createReactions(ReactionListDTO reactions) {
        for (ReactionDTO reaction :
                reactions.getReactions()) {
            createReaction(reaction);
        }
    }

    @Override
    public void deleteReaction(ReactionDTO reactionDTO) {
        reactionRepository.delete(reactionRepository.findByUserIdAndPostId(reactionDTO.getUserId(),
                reactionDTO.getPostId()).getId());
    }

    @Override
    public void deleteReactions(ReactionListDTO reactions) {
        for (ReactionDTO reaction :
                reactions.getReactions()) {
            deleteReaction(reaction);
        }
    }

}
