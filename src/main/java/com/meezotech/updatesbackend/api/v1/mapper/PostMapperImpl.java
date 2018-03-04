package com.meezotech.updatesbackend.api.v1.mapper;

import com.meezotech.updatesbackend.api.v1.model.PostDTO;
import com.meezotech.updatesbackend.domain.Post;
import com.meezotech.updatesbackend.domain.Reaction;
import org.springframework.stereotype.Component;

@Component
public class PostMapperImpl implements PostMapper {

    private GroupMapper groupMapper;
    private UserMapper userMapper;
    private MediaMapper mediaMapper;

    public PostMapperImpl(GroupMapper groupMapper, UserMapper userMapper, MediaMapper mediaMapper) {
        this.groupMapper = groupMapper;
        this.userMapper = userMapper;
        this.mediaMapper = mediaMapper;
    }

    @Override
    public PostDTO postToPostDto(Post post) {
        if (post == null) {
            return null;
        }
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setDate(post.getDate());
        postDTO.setText(post.getText());
        postDTO.setGroupDTO(groupMapper.groupToGroupDto(post.getGroup()));
        postDTO.setUserDTO(userMapper.userToUserDto(post.getUser()));
        postDTO.setMedia(mediaMapper.mediaListToMediaDtoList(post.getMedia()));
        postDTO.setNumberOfReactions((long) post.getReactions().size());
        postDTO.setNumberOfComments((long) post.getComments().size());
        postDTO.setReacted(checkIfUserReactedToThisPost(post, post.getUser().getId()));

        return postDTO;
    }

    private boolean checkIfUserReactedToThisPost(Post post, Long userId) {
        for (Reaction reaction:
             post.getReactions()) {
            if(reaction.getPost().getId().equals(post.getId())
                    && reaction.getUser().getId().equals(userId))
                return true;
        }
        return false;
    }


    @Override
    public Post postDtoToPost(PostDTO postDTO) {
        if (postDTO == null) {
            return null;
        }
        Post post = new Post();
        post.setId(postDTO.getId());
        post.setDate(postDTO.getDate());
        post.setText(postDTO.getText());
        post.setMedia(mediaMapper.mediaDtoListToMediaList(postDTO.getMedia()));
        post.setGroup(groupMapper.groupDtoToGroup(postDTO.getGroupDTO()));
        post.setUser(userMapper.userDtoToUser(postDTO.getUserDTO()));

        return post;
    }

}
