package Services.product;

import Model.Products;
import Repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindProductByCategoryService {
    @Autowired
    private ProductRepository productRepository;

    public List<Products> searchProductByCategory(int category_id){
        return productRepository.FindByCategory(category_id);
    }
}
