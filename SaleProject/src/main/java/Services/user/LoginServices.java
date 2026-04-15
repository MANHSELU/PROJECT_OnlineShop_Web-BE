package Services.user;

import DTO.LoginDTO;
import Exceptions.AppException;
import Exceptions.ErrorCode;
import Model.Users;
import Repository.user.UserRepository;
import Services.email.EmailService;
import Util.JwtUtil;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
public class LoginServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private EmailService emailService;

    // Hàm Login
    public String Login(LoginDTO loginDTO) throws MessagingException {
        Users users = userRepository.FindByEmail(loginDTO.getEmail());
        if (users == null) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        if (!passwordEncoder.matches(loginDTO.getPassword(), users.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }
        if (!users.isIs_account_enabled()) {
            if(users.getToken() == null || users.getToken_expired_at().isBefore(LocalDateTime.now())){
                String token = UUID.randomUUID().toString();
                users.setToken(token);
                users.setToken_created_at(LocalDateTime.now());
                users.setToken_expired_at(LocalDateTime.now().plusMinutes(30));
                userRepository.save(users);
                String verifyLink = "http://localhost:8080/api/verify?token=" + token;
                emailService.sendEmail(loginDTO.getEmail(),"Account verification, Click on the link to verify your account: ",verifyLink);
                throw new AppException(ErrorCode.USER_NOT_ENABLED);
            }
            else{
                throw new AppException(ErrorCode.USER_NOT_ENABLED_NOT_EXPIRED);
            }
        }
        if(users.getStatus().equals(Users.Status.BANNED)){
            throw new AppException(ErrorCode.USER_BANNED);
        }
        return jwtUtil.generateToken(users.getEmail());
    }

    public Map<String, Serializable> getUser(String token){
        if(!jwtUtil.validateToken(token)){
            throw new RuntimeException("Invalid token");
        }
        String userName = jwtUtil.getSubject(token);
        Users users = userRepository.FindByEmail(userName);
        if(users == null){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        return Map.of(
                "userID",users.getUser_id(),
                "userName",users.getUser_name(),
                "email",users.getEmail(),
                "role",users.getRole(),
                "enabled",users.isIs_account_enabled()
        );
    }
}
