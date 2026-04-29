package Mapper;

import DTO.CategoryDTO;
import Model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryDTO toCategoryDTO(Category category) {
        return  new CategoryDTO(
                category.getCategory_name()
        );
    }
}
