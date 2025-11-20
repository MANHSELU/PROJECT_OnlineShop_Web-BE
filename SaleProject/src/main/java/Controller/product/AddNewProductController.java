package Controller.product;

import DTO.CreateProductDTO;
import Services.product.AddNewProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AddNewProductController {
    @Autowired
    private AddNewProductServices addNewProductServices;

    @PostMapping("/createNewProducts")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<?> createNewProducts(@RequestBody CreateProductDTO createProductDTO) {
        try {
            addNewProductServices.AddNewProduct(createProductDTO);
            return ResponseEntity.ok(Map.of("message", "Product has been added successfully"));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
