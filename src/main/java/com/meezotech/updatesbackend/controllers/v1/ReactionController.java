package com.meezotech.updatesbackend.controllers.v1;


import com.meezotech.updatesbackend.api.v1.model.ReactionListDTO;
import com.meezotech.updatesbackend.services.ReactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ReactionController.BASE_URL)
public class ReactionController {

     static final String BASE_URL = "/api/v1/reaction";

     private ReactionService reactionService;

    public ReactionController(ReactionService reactionService) {
        this.reactionService = reactionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createReactions(@RequestBody ReactionListDTO reactions){
         reactionService.createReactions(reactions);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteReactions(@RequestBody ReactionListDTO reactions){
        reactionService.deleteReactions(reactions);
    }

}
