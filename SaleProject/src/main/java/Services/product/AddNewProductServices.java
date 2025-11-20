package Services.product;

import DTO.CreateProductDTO;
import Model.Category;
import Model.Products;
import Repository.product.CategoryRepository;
import Repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddNewProductServices {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    public void AddNewProduct(CreateProductDTO createProductDTO) {
        Category category = categoryRepository.FindById(createProductDTO.getCategory_id());
        Products products = new Products();
        products.setProduct_name(createProductDTO.getProduct_name());
        products.setQuantity(createProductDTO.getQuantity());
        products.setDescription(createProductDTO.getDescription());
        products.setCategory(category);
        products.setProduct_price(createProductDTO.getCategory_price());
        productRepository.save(products);
    }
}
