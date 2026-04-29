package Controller.product;

import DTO.GetTopProductDTO;
import Services.product.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GetTopProductListController {
    @Autowired
    private ProductServiceImpl productServiceImpl;

    @GetMapping("/topProducts")
    public ResponseEntity<?> getTopProducts (){
        try {
            List<GetTopProductDTO> productsList = productServiceImpl.getTopProducts();
            return ResponseEntity.status(200).body(productsList);
        }catch (Exception ex){
            return ResponseEntity.status(400).body(Map.of("message", ex.getMessage()));
        }
    }
}
