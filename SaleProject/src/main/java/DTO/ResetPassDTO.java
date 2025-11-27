package DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ResetPassDTO {
    @NotBlank(message = "Email Name không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;
    @NotBlank(message = "OTP không được để trống")
    private String otp;
    @NotBlank(message = "Password không được để trống")
    @Size(min = 8, max = 20, message = "Password phải từ 8-20 ký tự")
    // Ít nhất 1 chữ hoa, 1 chữ thường, 1 số, 1 ký tự đặc biệt
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
            message = "Password phải có chữ hoa, chữ thường, số và ký tự đặc biệt"
    )
    private String newPass;
    @NotBlank(message = "Confirm Password không được để trống")
    private String confirmNewPass;

    public String getEmail() {
        return email;
    }

    public String getOtp() {
        return otp;
    }

    public String getNewPass() {
        return newPass;
    }

    public String getConfirmNewPass() {
        return confirmNewPass;
    }
}
