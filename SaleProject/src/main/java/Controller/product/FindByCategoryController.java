package Controller.product;

import Model.Products;
import Services.product.FindProductByCategoryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FindByCategoryController {
    @Autowired
    private FindProductByCategoryService findProductByCategoryService;
    @GetMapping("/findByCategory")
    public ResponseEntity<?> findProductByCategory(HttpServletRequest request) {
        try {
            int category_id = Integer.parseInt(request.getParameter("category_id"));
            List<Products> productList =findProductByCategoryService.searchProductByCategory(category_id);
            return ResponseEntity.ok(Map.of("success",productList));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
