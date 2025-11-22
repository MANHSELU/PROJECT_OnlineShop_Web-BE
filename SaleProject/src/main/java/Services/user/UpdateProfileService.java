package Services.user;

import DTO.UpdateProfileDTO;
import Exceptions.AppException;
import Exceptions.ErrorCode;
import Model.Users;
import Repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateProfileService {
    @Autowired
    private UserRepository userRepository;
    public void updateProfile(int user_id, UpdateProfileDTO updateProfileDTO) {
        Users users = userRepository.FindById(user_id);
        if(users == null){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        users.setUser_name(updateProfileDTO.getUserName());
        users.setPhone(updateProfileDTO.getPhone());
        users.setAddress(updateProfileDTO.getAddress());
        userRepository.save(users);
    }
}
