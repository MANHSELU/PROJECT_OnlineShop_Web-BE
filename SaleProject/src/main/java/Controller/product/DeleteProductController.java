package Controller.product;

import Services.product.DeleteProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class DeleteProductController {
    @Autowired
    private DeleteProductService deleteProductService;
    @DeleteMapping("/deleteProduct")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<?> deleteProduct(@RequestParam int productId){
        try {
            deleteProductService.deleteProduct(productId);
            return ResponseEntity.ok(Map.of("message", "Product deleted successfully"));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
