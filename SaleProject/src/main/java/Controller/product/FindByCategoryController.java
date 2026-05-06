package Controller.product;

import DTO.GetAllProductDTO;
import Exceptions.AppException;
import Services.product.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FindByCategoryController {
    @Autowired
    private ProductServiceImpl productServiceImpl;

    @GetMapping("/findByCategory")
    public ResponseEntity<?> findProductByCategory(HttpServletRequest request) {
        try {
            int category_id = Integer.parseInt(request.getParameter("category_id"));
            List<GetAllProductDTO> productList = productServiceImpl.getAllProductsByCategory(category_id);
            return ResponseEntity.status(200).body(productList);
        } catch (AppException ex) {
            return ResponseEntity.status(404).body(Map.of("message", ex.getMessage()));
        }
    }
}
