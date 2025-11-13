package Services.user;

import DTO.LoginDTO;
import Exceptions.AppException;
import Exceptions.ErrorCode;
import Model.Users;
import Repository.user.UserRepository;
import Util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    public String Login(LoginDTO loginDTO, HttpServletResponse response) {
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
        return jwtUtil.generateToken(users.getEmail()); // Tạo token cho người dùng có tên đó sau khi login;

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
