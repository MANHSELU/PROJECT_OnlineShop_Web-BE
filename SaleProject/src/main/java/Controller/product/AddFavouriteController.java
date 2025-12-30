package Controller.product;

import Exceptions.AppException;
import Model.Favourite_Products;
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
    public ResponseEntity<?> AddFavouritesController(Authentication authentication, HttpServletRequest request) {
        try {
            String email = (String) authentication.getPrincipal();
            Users users = userRepository.FindByEmail(email);
            int product_id = Integer.parseInt(request.getParameter("product_id"));
            Favourite_Products favouriteProducts = addFavouriteServices.AddFavouriteProduct(users.getUser_id(), product_id);
            return ResponseEntity.status(201).body(favouriteProducts);
        } catch (AppException ex) {
            return ResponseEntity.status(404).body(Map.of("message", ex.getMessage()));
        }
    }
}
