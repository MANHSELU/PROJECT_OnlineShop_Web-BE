package Controller.product;

import DTO.UpdateProductDTO;
import Exceptions.AppException;
import Model.Products;
import Services.product.UpdateProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UpdateProductController {
    @Autowired
    private UpdateProductServices updateProductServices;

    @PatchMapping("/updateProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateProduct(@RequestParam("product_id") int product_id, @ModelAttribute UpdateProductDTO updateProductDTO, @RequestParam("images") MultipartFile[] file) {
        try {
            Products products = updateProductServices.updateProduct(product_id
                    , updateProductDTO.getProduct_name()
                    , updateProductDTO.getCategory_id()
                    , updateProductDTO.getQuantity()
                    , updateProductDTO.getDescription()
                    , updateProductDTO.getProduct_price()
                    , file);
            return ResponseEntity.status(204).body(products);
        } catch (AppException | IOException ex) {
            return ResponseEntity.status(404).body(Map.of("message", ex.getMessage()));
        }
    }
}
