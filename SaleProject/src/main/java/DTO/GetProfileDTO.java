package DTO;

import Model.Users;

public class GetProfileDTO {
    private String email;
    private String userName;
    private Role role;
    public enum Role {
        USER,
        ADMIN,
        MODERATOR
    }
    public GetProfileDTO() {
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
