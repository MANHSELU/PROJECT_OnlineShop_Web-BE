package Services;

import DTO.LoginDTO;
import Exceptions.AppException;
import Exceptions.ErrorCode;
import Model.Users;
import Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String Login(LoginDTO loginDTO) {
        Users users = userRepository.FindByEmail(loginDTO.getEmail());
        if (users == null) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        if (!users.isIs_account_enabled()) {
            throw new AppException(ErrorCode.USER_NOT_ENABLED);
        }
        if (!passwordEncoder.matches(loginDTO.getPassword(), users.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }
        return "Đăng nhập thành công";

    }
}
