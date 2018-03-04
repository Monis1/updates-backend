package com.meezotech.updatesbackend.api.v1.mapper;

import com.meezotech.updatesbackend.api.v1.model.ReactionDTO;
import com.meezotech.updatesbackend.domain.Reaction;

public interface ReactionMapper {

    Reaction reactionDtoToReaction(ReactionDTO reactionDTO);

    ReactionDTO reactionToReactionDto(Reaction reaction);

}
