package Controller.product;

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
    public ResponseEntity<?> filterProductByPriceASC(){
        try {
            List<Products> productsList = filterProductsServices.findProductsByPriceASC();
            return ResponseEntity.ok(Map.of("message",productsList));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/filterProductByPriceDESC")
    public ResponseEntity<?> filterProductByPriceDESC(){
        try {
            List<Products> productsList = filterProductsServices.findProductsByPriceDESC();
            return ResponseEntity.ok(Map.of("message",productsList));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/filterProductByNameASC")
    public ResponseEntity<?> filterProductByNameASC(){
        try {
            List<Products> productsList = filterProductsServices.findProductsByNameASC();
            return ResponseEntity.ok(Map.of("message",productsList));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/filterProductByNameDESC")
    public ResponseEntity<?> filterProductByNameDESC(){
        try {
            List<Products> productsList = filterProductsServices.findProductsByNameDESC();
            return ResponseEntity.ok(Map.of("message",productsList));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
