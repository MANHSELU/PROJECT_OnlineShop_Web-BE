package Controller.user;

import DTO.UpdateProfileDTO;
import Model.Users;
import Repository.user.UserRepository;
import Services.user.UpdateProfileService;
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
    private UpdateProfileService updateProfileService;
    @Autowired
    private UserRepository userRepository;
    @PatchMapping("/updateProfile")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<?> UpdateProfile(Authentication authentication, @RequestBody UpdateProfileDTO updateProfileDTO){
    try {
        String email = (String) authentication.getPrincipal();
        Users users = userRepository.FindByEmail(email);
        updateProfileService.updateProfile(users.getUser_id(),updateProfileDTO);
        return ResponseEntity.ok(Map.of("message","update profile success"));
    }catch (Exception ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    }
}
