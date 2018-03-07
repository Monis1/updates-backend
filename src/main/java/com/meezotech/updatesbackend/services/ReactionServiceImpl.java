package com.meezotech.updatesbackend.services;

import com.meezotech.updatesbackend.api.v1.mapper.ReactionMapper;
import com.meezotech.updatesbackend.api.v1.model.ReactionDTO;
import com.meezotech.updatesbackend.api.v1.model.ReactionListDTO;
import com.meezotech.updatesbackend.repositories.ReactionRepository;
import org.springframework.stereotype.Service;

@Service
public class ReactionServiceImpl implements ReactionService {

    private ReactionRepository reactionRepository;
    private ReactionMapper reactionMapper;

    public ReactionServiceImpl(ReactionRepository reactionRepository, ReactionMapper reactionMapper) {
        this.reactionRepository = reactionRepository;
        this.reactionMapper = reactionMapper;
    }

    @Override
    public ReactionDTO createReaction(ReactionDTO reactionDTO) {
        return reactionMapper.reactionToReactionDto(reactionRepository
                .save(reactionMapper.reactionDtoToReaction(reactionDTO)));
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
        reactionRepository.delete(reactionMapper.reactionDtoToReaction(reactionDTO));
    }

    @Override
    public void deleteReactions(ReactionListDTO reactions) {
        for (ReactionDTO reaction :
                reactions.getReactions()) {
            deleteReaction(reaction);
        }
    }

}
