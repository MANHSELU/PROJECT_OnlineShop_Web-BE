package Controller.user;

import Model.Users;
import Services.user.GetProfileServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class GetProfileController {
    @Autowired
    private GetProfileServices getProfileServices;

    @GetMapping("/profile")
    @PreAuthorize("hasRole('MEMBER')") // Chỉ người dùng đăng nhập có role là member mới vào được api này.
    public Users getProfile(Authentication authentication) {
         try {
             String email = (String) authentication.getPrincipal();
             return getProfileServices.getProfile(email);
         }catch (Exception e){
             e.getMessage();
         }
         return null;
    }
}


