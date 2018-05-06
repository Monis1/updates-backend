package com.meezotech.updatesbackend.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date date;

    @Lob
    private String text;

    @ManyToOne
    private User user;

    @ManyToOne
    private Group group;

    private boolean approved;

    private boolean fromAdmin;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private Set<Media> media = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private Set<Reaction> reactions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private Set<Comment> comments = new HashSet<>();

    public void setId(Long value) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(Set<Reaction> reactions) {
        this.reactions = reactions;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public boolean isFromAdmin() {
        return fromAdmin;
    }

    public void setFromAdmin(boolean fromAdmin) {
        this.fromAdmin = fromAdmin;
    }
}

