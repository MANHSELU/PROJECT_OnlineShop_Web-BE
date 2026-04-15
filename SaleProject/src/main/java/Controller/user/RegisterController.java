package Controller.user;

import DTO.RegisterDTO;
import Services.user.RegisterServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegisterController {
    @Autowired
    private RegisterServices registerServices;
    @Value("${frontend.url}")
    private String frontendUrl;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid  @RequestBody RegisterDTO registerDTO, BindingResult bindingResult) {
        try {
            if(bindingResult.hasErrors()) {
                String errorMessage = bindingResult
                        .getFieldErrors()
                        .get(0)
                        .getDefaultMessage();
                return ResponseEntity.status(400).body(Map.of("message", errorMessage));
            }
            registerServices.register(registerDTO);
            return ResponseEntity.status(201).body(Map.of("message", "Register Success. Please check your mail to get verify link"));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return ResponseEntity.status(400).body(Map.of("message", ex.getMessage()));

        }
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestParam String token){
        try {
            registerServices.verifyUser(token);
            return ResponseEntity.status(302)
                    .header("Location", frontendUrl + "/common/login?verified=true")
                    .build();
        }catch (Exception ex){
            return ResponseEntity.status(400).body(Map.of("message", ex.getMessage()));
        }
    }
}
