package Services.user;

import DTO.RegisterDTO;
import Exceptions.AppException;
import Model.Users;
import Repository.user.UserRepository;
import Services.email.EmailService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Exceptions.ErrorCode;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
@Transactional
public class RegisterServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${backend.url}")
    private String backendUrl;

    public void register(RegisterDTO registerDTO) throws MessagingException {
        Users user = userRepository.FindByEmail(registerDTO.getEmail());
        if (user != null) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        if (!registerDTO.getConfirmPassword().equals(registerDTO.getPassword())) {
            throw new AppException(ErrorCode.INVALID_CONFIRM_PASSWORD);
        }
        Users users = new Users();
        users.setUser_name(registerDTO.getUsername());
        users.setEmail(registerDTO.getEmail());
        users.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        users.setIs_account_enabled(false);
        users.setStatus(Users.Status.ACTIVE);
        String token = UUID.randomUUID().toString();
        users.setToken(token);
        users.setToken_created_at(LocalDateTime.now());
        users.setToken_expired_at(LocalDateTime.now().plusMinutes(30));
        userRepository.save(users);
        String verifyLink = backendUrl + "/api/verify?token=" + token;
        emailService.sendEmail(registerDTO.getEmail(),"Account verification, Click on the link to verify your account: ",verifyLink);
    }

    public void verifyUser(String token) {
        Optional<Users> users = userRepository.FindByToken(token);
        if (users.get().getToken_expired_at().isBefore(LocalDateTime.now())) {
            throw new AppException(ErrorCode.VERIFY_LINK_EXPIRED);
        }
        Users users1 = users.get();
        users1.setIs_account_enabled(true);
        users1.setRole(Users.Role.MEMBER);
        userRepository.save(users1);
    }
}
