package Controller.product;

import DTO.ReviewDTO;
import Exceptions.AppException;
import Model.Reviews;
import Model.Users;
import Repository.user.UserRepository;
import Services.product.AddReviewProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AddReviewController {
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
            Reviews reviews = addReviewProductServices.addReview(users.getUser_id(),product_id,reviewDTO.getRating(),reviewDTO.getComment());
            return ResponseEntity.status(201).body(reviews);
        }catch (AppException ex){
            return ResponseEntity.status(400).body(Map.of("message",ex.getMessage()));
        }
    }
}
