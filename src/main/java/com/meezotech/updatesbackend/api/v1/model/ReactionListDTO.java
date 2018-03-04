package com.meezotech.updatesbackend.api.v1.model;

import java.util.List;

public class ReactionListDTO {

    private List<ReactionDTO> reactions;

    public List<ReactionDTO> getReactions() {
        return reactions;
    }

    public void setReactions(List<ReactionDTO> reactions) {
        this.reactions = reactions;
    }
}
