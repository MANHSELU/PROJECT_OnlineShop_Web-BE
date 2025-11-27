package Services.user;

import Exceptions.AppException;
import Exceptions.ErrorCode;
import Model.Users;
import Repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ResetPassServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public void resetPass(String email, String otp, String newPassword, String confirmNewPassword){
        Users user = userRepository.FindByEmail(email);
        if(user == null){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        if(!otp.equals(user.getOtp()) || user.getOtp_expired_at().isBefore(LocalDateTime.now())){
            throw new AppException(ErrorCode.INVALID_OTP);
        }
        if(!newPassword.equals(confirmNewPassword)){
            throw new AppException(ErrorCode.INVALID_CONFIRM_PASSWORD);
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
