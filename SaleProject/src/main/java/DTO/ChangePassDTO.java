package DTO;

public class ChangePassDTO {
    private String email;
    private String newPassword;
    private String oldPassword;
    private  String newPasswordConfirm;
    public String getNewPassword() {
        return newPassword;
    }

    public String getNewPasswordConfirm() {
        return newPasswordConfirm;
    }

    public String getOldPassword() {
        return oldPassword;
    }
}
