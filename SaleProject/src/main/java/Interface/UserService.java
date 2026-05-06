package Interface;

import DTO.GetProfileDTO;
import DTO.UpdateProfileDTO;

public interface UserService {
    GetProfileDTO getProfile(String email);
    void updateProfile(int user_id, UpdateProfileDTO updateProfileDTO);
}
