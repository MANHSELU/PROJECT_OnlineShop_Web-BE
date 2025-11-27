package DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterDTO {
    @NotBlank(message = "User Name không được để trống")
    private String username;
    @NotBlank(message = "Email Name không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;
    @NotBlank(message = "Password không được để trống")
    @Size(min = 8, max = 20, message = "Password phải từ 8-20 ký tự")
    // Ít nhất 1 chữ hoa, 1 chữ thường, 1 số, 1 ký tự đặc biệt
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
            message = "Password phải có chữ hoa, chữ thường, số và ký tự đặc biệt"
    )
    private String password;
    @NotBlank(message = "Confirm Password không được để trống")
    private String confirmPassword;

    public RegisterDTO() {
    }

    public RegisterDTO(String username, String email, String password, String confirmPassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
