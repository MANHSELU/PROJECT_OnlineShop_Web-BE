package Controller.product;

import Model.Users;
import Repository.user.UserRepository;
import Services.product.AddFavouriteServices;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AddFavouriteController {
    @Autowired
    private AddFavouriteServices addFavouriteServices;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/AddFavourite")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<?> AddFavouritesController(Authentication authentication,HttpServletRequest request){
        try {
            String email = (String) authentication.getPrincipal();
            Users users = userRepository.FindByEmail(email);
            int product_id = Integer.parseInt(request.getParameter("product_id"));
            addFavouriteServices.AddFavouriteProduct(users.getUser_id(),product_id);
            return ResponseEntity.ok(Map.of("messages", "Added success"));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
