package com.meezotech.updatesbackend.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity(name = "Group_Table")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "groups")
    private Set<User> bannedUsers = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private Set<Post> posts = new HashSet<>();

    private boolean deleted;

    private boolean isTypeApproval;

    public Group() {
    }

    public Group(Long id) {
        this.id = id;
    }

    public Group(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public void setId(Long value) {
        this.id = value;
    }

    public Long getId() {
        return id;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return name;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isTypeApproval() {
        return isTypeApproval;
    }

    public void setTypeApproval(boolean typeApproval) {
        isTypeApproval = typeApproval;
    }

    public Set<User> getBannedUsers() {
        return bannedUsers;
    }

    public void setBannedUsers(Set<User> bannedUsers) {
        this.bannedUsers = bannedUsers;
    }
}

