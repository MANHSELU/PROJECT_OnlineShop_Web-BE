package DTO;

import Model.Users;

public class GetProfileDTO {
    private String email;
    private String userName;
    private Role role;
    private String address;
    private String phone;

    public GetProfileDTO(String email, String userName, Role role, String address, String phone) {
        this.email = email;
        this.userName = userName;
        this.role = role;
        this.address = address;
        this.phone = phone;
    }

    public enum Role {
        GUEST, MEMBER, ADMIN
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
