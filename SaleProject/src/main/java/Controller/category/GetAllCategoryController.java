package Controller.category;

import DTO.CategoryDTO;
import Services.product.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GetAllCategoryController {
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategories(){
        try {
            List<CategoryDTO> categoryList = categoryServiceImpl.getAllCategories();
            return ResponseEntity.status(200).body(categoryList);
        }catch (Exception ex){
            return ResponseEntity.status(400).body(Map.of("message",ex.getMessage()));
        }
    }
}
