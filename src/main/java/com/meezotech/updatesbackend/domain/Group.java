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

    @ManyToMany(mappedBy="groups")
    private Set<Rule> rules = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private Set<Post> posts = new HashSet<>();

    public Group(){}

    public Group(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
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


}

