package Services.admin;

import Exceptions.AppException;
import Exceptions.ErrorCode;
import Model.Users;
import Repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BanOrUnbanUserServices {
    @Autowired
    private UserRepository userRepository;
    public void BanUsers(int user_id){
        Users users = userRepository.FindById(user_id);
        if(users == null){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        users.setStatus(Users.Status.BANNED);
        userRepository.save(users);
    }

    public void UnBanUsers(int user_id){
        Users users = userRepository.FindById(user_id);
        if(users == null){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        users.setStatus(Users.Status.ACTIVE);
        userRepository.save(users);
    }
}
