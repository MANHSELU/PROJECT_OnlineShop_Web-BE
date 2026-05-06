package Mapper;

import DTO.GetProfileDTO;
import DTO.UpdateProfileDTO;
import Model.Users;
import Repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Autowired
    private UserRepository  userRepository;

    public GetProfileDTO toUserDTO (Users user) {
    return  new GetProfileDTO(
            user.getEmail(),
            user.getUser_name(),
            GetProfileDTO.Role.valueOf(user.getRole().name()),
            user.getAddress(),
            user.getPhone()
    );
    };

    public void toUserEntity (Users users, UpdateProfileDTO updateProfileDTO) {
        users.setUser_name(updateProfileDTO.getUserName());
        users.setAddress(updateProfileDTO.getAddress());
        users.setPhone(updateProfileDTO.getPhone());
    }
}
