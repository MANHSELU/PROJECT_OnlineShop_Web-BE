package Controller.user;

import DTO.LoginDTO;
import Services.user.LoginServices;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private LoginServices loginServices;

        @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO, HttpServletResponse response, BindingResult bindingResult) {
        try {
            if(bindingResult.hasErrors()) {
                String errorMessage = bindingResult
                        .getFieldErrors()
                        .get(0)
                        .getDefaultMessage();
                return ResponseEntity.status(400).body(Map.of("message", errorMessage));
            }
            String token = loginServices.Login(loginDTO);
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(3600);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);
            return ResponseEntity.status(200).body(Map.of("message", "Login Success"));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(Map.of("message", ex.getMessage()));
        }
    }

}
