package Services.user;

import DTO.ForgotPassDTO;
import Exceptions.AppException;
import Exceptions.ErrorCode;
import Model.Users;
import Repository.user.UserRepository;
import Services.email.EmailService;
import jakarta.mail.MessagingException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class ForgotPassService {
    @Autowired
    private UserRepository  userRepository;
    @Autowired
    private EmailService emailService;
    public void forgotPassRequest(String email) throws MessagingException {
        Users users = userRepository.FindByEmail(email);
        if(users==null){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        if (users.getOtp() != null && users.getOtp_expired_at() != null && users.getOtp_expired_at().isAfter(LocalDateTime.now())) {
            throw new AppException(ErrorCode.OTP_EXISTED);
        }
        String otp = RandomStringUtils.randomNumeric(6);
        emailService.sendEmail(email,"Mã xác nhận OTP : ",otp);
        users.setOtp(otp);
        users.setOtp_created_at(LocalDateTime.now());
        users.setOtp_expired_at(LocalDateTime.now().plusMinutes(2));
        userRepository.save(users);
    }
}
