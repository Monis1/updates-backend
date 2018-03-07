package com.meezotech.updatesbackend.api.v1.mapper;

import com.meezotech.updatesbackend.api.v1.model.ReactionDTO;
import com.meezotech.updatesbackend.domain.Post;
import com.meezotech.updatesbackend.domain.Reaction;
import com.meezotech.updatesbackend.domain.User;
import org.springframework.stereotype.Component;

@Component
public class ReactionMapperImpl implements ReactionMapper {

    @Override
    public Reaction reactionDtoToReaction(ReactionDTO reactionDTO) {
        if (reactionDTO == null) {
            return null;
        }
        Reaction reaction = new Reaction();

        User user = new User();
        user.setId(reactionDTO.getUserId());

        Post post = new Post();
        post.setId(reactionDTO.getPostId());

        reaction.setReacionType("");
        reaction.setPost(post);
        reaction.setUser(user);

        return reaction;
    }

    @Override
    public ReactionDTO reactionToReactionDto(Reaction reaction) {
        if (reaction == null) {
            return null;
        }
        return new ReactionDTO(reaction.getUser().getId(),
                reaction.getPost().getId(), "");
    }

}
