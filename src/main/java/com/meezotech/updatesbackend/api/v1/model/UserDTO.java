package com.meezotech.updatesbackend.api.v1.model;

public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String profilePictureUrl;
    private Long followers;
    private Long following;
    private Long numberOfPosts;

    public UserDTO() {
        followers = 0L;
        following = 0L;
        numberOfPosts = 0L;
    }

    public UserDTO(Long id, String firstName, String lastName, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public Long getFollowers() {
        return followers;
    }

    public void setFollowers(Long followers) {
        this.followers = followers;
    }

    public Long getFollowing() {
        return following;
    }

    public void setFollowing(Long following) {
        this.following = following;
    }

    public Long getNumberOfPosts() {
        return numberOfPosts;
    }

    public void setNumberOfPosts(Long numberOfPosts) {
        this.numberOfPosts = numberOfPosts;
    }
}
