package Controller.admin;

import Model.Users;
import Services.admin.BanOrUnbanUserServices;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class BanOrUnbanUserController {
    @Autowired
    private BanOrUnbanUserServices banOrUnbanUserServices;
    @PatchMapping("/banUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> BanUser(HttpServletRequest request){
       try {
           int user_id = Integer.parseInt(request.getParameter("user_id"));
           banOrUnbanUserServices.BanUsers(user_id);
           return ResponseEntity.ok(Map.of("messages","Success"));
       }catch (Exception e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }
    @PatchMapping("/unBanUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> UnBanUser(HttpServletRequest request){
        try {
            int user_id = Integer.parseInt(request.getParameter("user_id"));
            banOrUnbanUserServices.UnBanUsers(user_id);
            return ResponseEntity.ok(Map.of("messages","Success"));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
