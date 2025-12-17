package Controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RenderPageController {

    @GetMapping("/home")
    public String homeController(){
        return "Home";
    }
    @GetMapping("/loginPage")
    public String loginPage(){
        return "Login";
    }
}
