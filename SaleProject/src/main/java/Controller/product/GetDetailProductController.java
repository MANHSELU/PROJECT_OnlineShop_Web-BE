package Controller.product;


import Exceptions.AppException;
import Model.Products;
import Services.product.GetDetailProductsServices;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GetDetailProductController {
    @Autowired
    private GetDetailProductsServices getDetailProducts;

    @GetMapping("/GetDetailProduct")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<?> GetDetailController(HttpServletRequest request) {
        try {
            int product_id = Integer.parseInt(request.getParameter("product_id"));
            Products products = getDetailProducts.getDetailProducts(product_id);
            return ResponseEntity.status(200).body(products);
        } catch (AppException ex) {
            return ResponseEntity.status(404).body(Map.of("message", ex.getMessage()));
        }
    }

}
