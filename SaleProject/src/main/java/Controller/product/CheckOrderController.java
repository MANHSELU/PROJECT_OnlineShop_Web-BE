package Controller.product;


import Exceptions.AppException;
import Model.Order;
import Model.Users;
import Repository.user.UserRepository;
import Services.product.CheckOutOrderServices;
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
public class CheckOrderController {
    @Autowired
    private CheckOutOrderServices checkOutOrderServices;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/CheckOrder")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<?> checkOut(Authentication authentication, HttpServletRequest request, @RequestBody Double total_price) {
        try {
            String email = (String) authentication.getPrincipal();
            Users users = userRepository.FindByEmail(email);
            int product_id = Integer.parseInt(request.getParameter("product_id"));
            Order order = checkOutOrderServices.CheckoutOrder(users.getUser_id(), product_id, total_price);
            return ResponseEntity.status(200).body(order);
        } catch (AppException ex) {
            return ResponseEntity.status(404).body(Map.of("message", ex.getMessage()));
        }
    }
}
