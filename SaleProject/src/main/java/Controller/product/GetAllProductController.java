package Controller.product;

import Model.Products;
import Services.product.GetAllProductsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GetAllProductController {
    @Autowired
    private GetAllProductsServices getAllProductsServices;
    @GetMapping("/getAllProduct")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<?> getAllProduct(){
        try {
            List<Products> prodcutList = getAllProductsServices.getAllProducts();
            return ResponseEntity.ok(Map.of("Get All Products Sucessfully", prodcutList));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
