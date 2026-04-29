package Services.product;

import DTO.CategoryDTO;
import Interface.CategoryService;
import Mapper.CategoryMapper;
import Model.Category;
import Repository.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> getAllCategories() {
       List<Category> categories = categoryRepository.findAll();
       return categories.stream().map(categoryMapper::toCategoryDTO).toList();
    }
}
