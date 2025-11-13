package Services.user;

import Exceptions.AppException;
import Exceptions.ErrorCode;
import Model.Users;
import Repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetProfileService {
    @Autowired
    private UserRepository userRepository;
    public Users  getProfile(String email) {
        Users users = userRepository.FindByEmail(email);
        if(users == null) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        return users;

    }
}
