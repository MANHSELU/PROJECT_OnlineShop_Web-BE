package Controller.admin;

import Model.Users;
import Services.admin.GetAllUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GetAllUserController {
    @Autowired
    private GetAllUserServices getAllUserServices;
    @GetMapping("/getAllUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<Users> usersList = getAllUserServices.findAllUser();
            return ResponseEntity.ok(Map.of("users", usersList));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
