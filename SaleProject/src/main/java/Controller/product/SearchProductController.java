package Controller.product;

import Exceptions.AppException;
import Model.Products;
import Services.product.SearchProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SearchProductController {
    @Autowired
    private SearchProductServices searchProductServices;

    @GetMapping("/searchProduct")
    public ResponseEntity<?> search(@RequestParam("keyword") String keyword) {
        try {
            List<Products> products = searchProductServices.searchProduct(keyword);
            return ResponseEntity.status(200).body(products);
        } catch (AppException ex) {
            return ResponseEntity.status(404).body(Map.of("message", ex.getMessage()));
        }
    }
}
