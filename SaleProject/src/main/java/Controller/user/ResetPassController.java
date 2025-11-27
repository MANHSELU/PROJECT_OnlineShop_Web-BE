package Controller.user;

import DTO.ResetPassDTO;
import Repository.user.UserRepository;
import Services.user.ResetPassServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ResetPassController {
    @Autowired
    private ResetPassServices resetPassServices;
    @PatchMapping("/resetPass")
    public ResponseEntity<?> resetPass(@RequestBody ResetPassDTO resetPassDTO) {
        try {
            resetPassServices.resetPass(resetPassDTO.getEmail(),resetPassDTO.getOtp(),resetPassDTO.getNewPass(),resetPassDTO.getConfirmNewPass());
            return ResponseEntity.ok(Map.of("message", "Reset Pass Successfully !"));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
