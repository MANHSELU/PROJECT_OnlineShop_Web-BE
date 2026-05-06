package Controller.user;

import DTO.UpdateProfileDTO;
import Model.Users;
import Repository.user.UserRepository;
import Services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UpdateProfileController {
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private UserRepository userRepository;

    @PatchMapping("/updateProfile")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<?> UpdateProfile(Authentication authentication, @RequestBody UpdateProfileDTO updateProfileDTO){
    try {
        String email = (String) authentication.getPrincipal();
        Users users = userRepository.FindByEmail(email);
        userServiceImpl.updateProfile(users.getUser_id(),updateProfileDTO);
        return ResponseEntity.status(200).body(Map.of("message", "Profile Updated Successfully"));
    }catch (Exception ex){
        return ResponseEntity.status(400).body(Map.of("message", ex.getMessage()));
    }
    }
}
