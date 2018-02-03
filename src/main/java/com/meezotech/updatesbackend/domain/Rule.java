package com.meezotech.updatesbackend.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "Group_Rule",
            joinColumns = @JoinColumn(name = "rule_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups = new HashSet<>();

    private void setId(Long value) {
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

    public Set getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

}

