package Controller.product;

import DTO.ChangePassDTO;
import Model.Users;
import Repository.user.UserRepository;
import Services.user.ChangePassServices;
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
public class ChangePassController {
    @Autowired
    private ChangePassServices changePassServices;
    @Autowired
    private UserRepository userRepository;
    @PatchMapping("/changePass")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<?> changePassController(Authentication authentication, @RequestBody ChangePassDTO changePassDTO) {
        try {
            String email = (String) authentication.getPrincipal();
            Users users = userRepository.FindByEmail(email);
            changePassServices.changPass(users.getUser_id(),changePassDTO);
            return ResponseEntity.ok(Map.of("message", "change Pass Success"));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
