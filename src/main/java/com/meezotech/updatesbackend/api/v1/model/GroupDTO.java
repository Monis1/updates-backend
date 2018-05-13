package com.meezotech.updatesbackend.api.v1.model;

public class GroupDTO {

    private Long id;
    private String name;
    private boolean isDeleted;
    private boolean isTypeApproval;
    private String adminName;
    private String adminEmail;
    private String adminPassword;

    public boolean isTypeApproval() {
        return isTypeApproval;
    }

    public void setTypeApproval(boolean typeApproval) {
        isTypeApproval = typeApproval;
    }

    public GroupDTO(){}

    public GroupDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

}
