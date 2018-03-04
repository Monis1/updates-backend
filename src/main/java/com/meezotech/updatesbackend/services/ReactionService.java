package com.meezotech.updatesbackend.services;

import com.meezotech.updatesbackend.api.v1.model.ReactionDTO;
import com.meezotech.updatesbackend.api.v1.model.ReactionListDTO;

public interface ReactionService {

    ReactionDTO createReaction(ReactionDTO reactionDTO);

    void createReactions(ReactionListDTO reactions);

    void deleteReaction(ReactionDTO reactionDTO);

    void deleteReactions(ReactionListDTO reactions);

}
