package Controller.product;

import DTO.CreateProductDTO;
import Services.product.AddNewProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AddNewProductController {
    @Autowired
    private AddNewProductServices addNewProductServices;

    @PostMapping("/createNewProducts")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createNewProducts(@ModelAttribute CreateProductDTO createProductDTO , @RequestParam("images") MultipartFile[] files) {
        try {
            addNewProductServices.AddNewProduct(createProductDTO,files);
            return ResponseEntity.ok(Map.of("message", "Product has been added successfully"));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
