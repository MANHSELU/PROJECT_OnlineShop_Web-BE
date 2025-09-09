package Controller;


import DTO.RegisterDTO;
import Services.RegisterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController // @RestController trả về JSON/XML còn @Controller trả về view theo kiểu HTML,JSP,...
@RequestMapping("/api")
public class RegisterController {
    @Autowired
    private RegisterServices registerServices;

    @PostMapping("/register")
    public String register(@RequestBody RegisterDTO  registerDTO){
        try {
            registerServices.register(registerDTO);
            return "Register Success";
        }catch (Exception ex){
            return ex.getMessage();
        }
    }
    @PostMapping("/verify")
    public String verifyUser(@RequestParam String token){
        try {
            registerServices.verifyUser(token);
            return "Verify Success";
        }catch (Exception ex){
            return ex.getMessage();
        }
    }
}
