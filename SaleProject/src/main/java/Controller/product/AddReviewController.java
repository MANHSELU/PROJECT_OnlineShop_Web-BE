package Controller.product;

import DTO.ReviewDTO;
import Model.Users;
import Repository.product.ReviewRepository;
import Repository.user.UserRepository;
import Services.product.AddReviewProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AddReviewController {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddReviewProductServices addReviewProductServices;
    @PostMapping("/addReview")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<?> addReview(@RequestParam("product_id") int product_id, @RequestBody ReviewDTO reviewDTO, Authentication authentication) {
        try {
            String email = (String) authentication.getPrincipal();
            Users users = userRepository.FindByEmail(email);
            addReviewProductServices.addReview(users.getUser_id(),product_id,reviewDTO.getRating(),reviewDTO.getComment());
            return ResponseEntity.ok(Map.of("message", "Successfully added review"));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
