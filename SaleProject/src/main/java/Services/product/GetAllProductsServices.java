package Services.product;

import Model.Products;
import Repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsServices {
    @Autowired
    private ProductRepository productRepository;
    public List<Products> getAllProducts(){
        return productRepository.findAll();
    }
}
