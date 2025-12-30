package Controller.product;

import DTO.CreateProductDTO;
import Exceptions.AppException;
import Model.Products;
import Services.product.AddNewProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AddNewProductController {
    @Autowired
    private AddNewProductServices addNewProductServices;

    @PostMapping("/createNewProducts")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createNewProducts(@ModelAttribute CreateProductDTO createProductDTO, @RequestParam("images") MultipartFile[] files) {
        try {
            Products products = addNewProductServices.AddNewProduct(createProductDTO, files);
            return ResponseEntity.status(201).body(products);
        } catch (AppException | IOException ex) {
            return ResponseEntity.status(400).body(Map.of("message", ex.getMessage()));
        }
    }
}
