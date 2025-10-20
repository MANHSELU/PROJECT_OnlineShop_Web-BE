package Controller;

import Model.Users;
import Repository.UserRepository;
import Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AddTooShoppingCartController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/AddShoppingCart")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<?> AddToShoppingCart(Authentication authentication, HttpServletRequest request, @RequestBody int quantity ) {
        try {
            String email = (String) authentication.getPrincipal();
            Users users = userRepository.FindByEmail(email);
            int product_id  = Integer.parseInt(request.getParameter("product_id"));
            userService.addToShoppingCart(users.getUser_id(),product_id,quantity);
            return ResponseEntity.ok(Map.of(
                    "messages", "Added success"
            ));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
