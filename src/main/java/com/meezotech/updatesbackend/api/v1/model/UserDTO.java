package com.meezotech.updatesbackend.api.v1.model;

public class UserDTO {

    private String firstName;
    private String LastName;
    private String email;

    public UserDTO(String firstName, String lastName, String email) {
        this.firstName = firstName;
        LastName = lastName;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
