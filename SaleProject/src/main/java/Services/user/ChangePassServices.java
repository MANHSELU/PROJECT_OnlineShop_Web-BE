package Services.user;

import DTO.ChangePassDTO;
import Exceptions.AppException;
import Exceptions.ErrorCode;
import Model.Users;
import Repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ChangePassServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void changPass(int user_id, ChangePassDTO changePassDTO){
        Users users = userRepository.FindById(user_id);
        if(users == null){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        if(!passwordEncoder.matches(changePassDTO.getOldPassword(), users.getPassword())){ // sử dụng matches trong password encode
            throw new AppException(ErrorCode.OLD_PASS_IS_INVALID);
        }
        if(passwordEncoder.matches(changePassDTO.getNewPassword(), users.getPassword())){
            throw new AppException(ErrorCode.PASS_IS_DUPLICATED);
        }
        if(!changePassDTO.getNewPassword().equals(changePassDTO.getNewPasswordConfirm())){
            throw new AppException(ErrorCode.NEW_PASS_IS_NOT_CONFIRMED);
        }
        users.setPassword(passwordEncoder.encode(changePassDTO.getNewPassword()));
        userRepository.save(users);
    }
}
