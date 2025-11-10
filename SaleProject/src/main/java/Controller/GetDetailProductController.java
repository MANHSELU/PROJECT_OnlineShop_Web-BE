package Controller;


import Services.GetDetailProducts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GetDetailProductController {
    @Autowired
    private GetDetailProducts getDetailProducts;

    @GetMapping("/GetDetailProduct")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<?> GetDetailController(HttpServletRequest request){
        try {
            int product_id = Integer.parseInt(request.getParameter("product_id"));
            getDetailProducts.getDetailProducts(product_id);
            return ResponseEntity.ok(Map.of("Get Detail Products Sucessfully", getDetailProducts.getDetailProducts(product_id)));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
