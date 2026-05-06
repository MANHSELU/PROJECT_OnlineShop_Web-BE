package Controller.user;

import DTO.GetProfileDTO;
import Interface.UserService;
import Model.Users;
import Services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api")
public class GetProfileController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/profile")
   @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<?> getProfile(Authentication authentication) {
         try {
             String email = (String) authentication.getPrincipal();
             GetProfileDTO getProfileDTO = userServiceImpl.getProfile(email);
             return ResponseEntity.status(200).body(getProfileDTO);
         }catch (Exception ex){
             return ResponseEntity.status(404).body(Map.of("message", ex.getMessage()));
         }
    }
}


