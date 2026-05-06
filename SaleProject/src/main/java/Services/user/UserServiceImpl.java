package Services.user;

import DTO.GetProfileDTO;
import DTO.UpdateProfileDTO;
import Exceptions.AppException;
import Exceptions.ErrorCode;
import Interface.UserService;
import Mapper.UserMapper;
import Model.Users;
import Repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public GetProfileDTO getProfile(String email) {
        Users users = userRepository.FindByEmail(email);
        if(users == null) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        return userMapper.toUserDTO(users);
    }

    @Override
    public void updateProfile(int user_id, UpdateProfileDTO updateProfileDTO) {
        Users users = userRepository.findById(user_id).orElseThrow(()  -> new AppException(ErrorCode.USER_NOT_EXISTED));
        if(users == null) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        userMapper.toUserEntity(users,updateProfileDTO);
        userRepository.save(users);
    }
}
