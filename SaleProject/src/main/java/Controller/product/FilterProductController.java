package Controller.product;

import Exceptions.AppException;
import Model.Products;
import Services.product.FilterProductsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FilterProductController {
    @Autowired
    private FilterProductsServices filterProductsServices;

    @GetMapping("/filterProductByPriceASC")
    public ResponseEntity<?> filterProductByPriceASC() {
        try {
            List<Products> productsList = filterProductsServices.findProductsByPriceASC();
            return ResponseEntity.status(200).body(productsList);
        } catch (AppException ex) {
            return ResponseEntity.status(404).body(Map.of("message", ex.getMessage()));
        }
    }

    @GetMapping("/filterProductByPriceDESC")
    public ResponseEntity<?> filterProductByPriceDESC() {
        try {
            List<Products> productsList = filterProductsServices.findProductsByPriceDESC();
            return ResponseEntity.status(200).body(productsList);
        } catch (AppException ex) {
            return ResponseEntity.status(404).body(Map.of("message", ex.getMessage()));
        }
    }

    @GetMapping("/filterProductByNameASC")
    public ResponseEntity<?> filterProductByNameASC() {
        try {
            List<Products> productsList = filterProductsServices.findProductsByNameASC();
            return ResponseEntity.status(200).body(productsList);
        } catch (AppException ex) {
            return ResponseEntity.status(404).body(Map.of("message", ex.getMessage()));
        }
    }

    @GetMapping("/filterProductByNameDESC")
    public ResponseEntity<?> filterProductByNameDESC() {
        try {
            List<Products> productsList = filterProductsServices.findProductsByNameDESC();
            return ResponseEntity.status(200).body(productsList);
        } catch (AppException ex) {
            return ResponseEntity.status(404).body(Map.of("message", ex.getMessage()));
        }
    }

}
