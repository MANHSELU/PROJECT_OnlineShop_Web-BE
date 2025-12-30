package Controller.product;

import Exceptions.AppException;
import Services.product.DeleteProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DeleteProductController {
    @Autowired
    private DeleteProductServices deleteProductService;

    @DeleteMapping("/deleteProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProduct(@RequestParam int productId) {
        try {
            deleteProductService.deleteProduct(productId);
            return ResponseEntity.status(204).build();
        } catch (AppException | IOException ex) {
            return ResponseEntity.status(404).body(Map.of("message", ex.getMessage()));
        }
    }
}
