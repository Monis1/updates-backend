package com.meezotech.updatesbackend.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String text;

    @ManyToMany
    @JoinTable(name = "User_Post",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "Group_Post",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private Set<Media> media = new HashSet<>();

    private void setId(Long value) {
        this.id = value;
    }

    public Long getId() {
        return id;
    }

    public void setText(String value) {
        this.text = value;
    }

    public String getText() {
        return text;
    }

    public void setMedia(Set<Media> value) {
        this.media = value;
    }

    public Set<Media> getMedia() {
        return media;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
}

