package Controller;

import DTO.LoginDTO;
import Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        try {
            loginService.Login(loginDTO);
            return "Login Success";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
}
