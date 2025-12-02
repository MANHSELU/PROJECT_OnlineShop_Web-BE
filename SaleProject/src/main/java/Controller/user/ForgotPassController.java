package Controller.user;

import DTO.ForgotPassDTO;
import Services.user.ForgotPassServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ForgotPassController {
    @Autowired
    private ForgotPassServices forgotPassService;
    @PostMapping("/sendOTP")
    public ResponseEntity<?> forgotPass(@RequestBody ForgotPassDTO forgotPassDTO) {
        try {
            forgotPassService.forgotPassRequest(forgotPassDTO.getEmail());
            return ResponseEntity.ok(Map.of("messages", "Send OTP Success"));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
