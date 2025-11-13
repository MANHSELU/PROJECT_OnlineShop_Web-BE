package Services.user;

import DTO.RegisterDTO;
import Exceptions.AppException;
import Model.Users;
import Repository.user.UserRepository;
import Services.email.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Exceptions.ErrorCode;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
public class RegisterServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean isValidPass(String password) {
        return password.length() >= 8 && password.matches(".*\\d.*") && password.matches(".*[a-zA-Z].*");
    }

    public void register(RegisterDTO registerDTO) throws MessagingException {
        Users user = userRepository.FindByEmail(registerDTO.getEmail());
        if (user != null) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        if (!registerDTO.getConfirmPassword().equals(registerDTO.getPassword())) {
            throw new AppException(ErrorCode.INVALID_CONFIRM_PASSWORD);
        }
        if (!isValidPass(registerDTO.getPassword())){
            throw new AppException(ErrorCode.INVALID_PASSWORD_SYNTAX);
        }
        Users users = new Users();
        users.setUser_name(registerDTO.getUsername());
        users.setEmail(registerDTO.getEmail());
        users.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        users.setIs_account_enabled(false);
        String token = UUID.randomUUID().toString();
        users.setToken(token);
        users.setToken_created_at(LocalDateTime.now());
        users.setToken_expired_at(LocalDateTime.now().plusMinutes(30));
        userRepository.save(users);
        String verifyLink = "http://localhost:8080/api/verify?token=" + token;
        emailService.sendEmail(registerDTO.getEmail(),"Xác thực tài khoản ,Nhấn vào link để xác thực tài khoản: ",verifyLink);
    }

    public void verifyUser(String token) {
        Optional<Users> users = userRepository.FindByToken(token); // ===> Optional là kiểu dữ liệu đặc biệt trong java8
                                                                  // ===> Optional giống như 1 cái hộp, nếu có giá trị thì lấy giá trị
                                                                  // từ cái hộp đó ra và dùng nó, còn ko thì hộp rỗng và tránh trả về lỗi NullPointException
        if (users.get().getToken_expired_at().isBefore(LocalDateTime.now())) {
            throw new AppException(ErrorCode.VERIFY_LINK_EXPIRED);
        }
        Users users1 = users.get();
        users1.setIs_account_enabled(true);
        users1.setRole(Users.Role.MEMBER);
        userRepository.save(users1);
    }
}
