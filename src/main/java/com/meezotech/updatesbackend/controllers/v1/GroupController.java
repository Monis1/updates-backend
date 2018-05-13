package com.meezotech.updatesbackend.controllers.v1;

import com.meezotech.updatesbackend.api.v1.model.GroupDTO;
import com.meezotech.updatesbackend.api.v1.model.GroupListDTO;
import com.meezotech.updatesbackend.services.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(GroupController.BASE_URL)
public class GroupController {

    public static final String BASE_URL = "/api/v1/group";

    private GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public GroupListDTO getAllGroups(){
        return groupService.getAllGroups();
    }

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.OK)
    public GroupDTO createGroup(@RequestBody GroupDTO groupDTO){
        return groupService.createGroup(groupDTO);
    }

    @PostMapping("/admin/hide")
    @ResponseStatus(HttpStatus.OK)
    public void hideGroup(@RequestBody GroupDTO groupDTO){
         groupService.hideGroup(groupDTO);
    }

    @PostMapping("/admin/unhide")
    @ResponseStatus(HttpStatus.OK)
    public void unHideGroup(@RequestBody GroupDTO groupDTO){
        groupService.unHideGroup(groupDTO);
    }

    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public boolean doesAdminExists(@RequestParam("groupId") long groupId,
                                   @RequestParam("password") String password){
        return groupService.doesAdminExists(groupId, password);
    }

}
