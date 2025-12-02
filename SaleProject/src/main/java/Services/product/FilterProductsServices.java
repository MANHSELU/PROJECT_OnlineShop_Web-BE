package Services.product;

import Model.Products;
import Repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterProductsServices {
    @Autowired
    private ProductRepository productRepository;
    // Hàm filter sản phẩm theo giá cao->thấp
    public List<Products> findProductsByPriceASC(){
        return productRepository.FindByPriceASC();
    }
    // Hàm filter sản phẩm theo giá thấp->cao
    public List<Products> findProductsByPriceDESC(){
        return productRepository.FindByPriceDESC();
    }
    // Hàm filter sản phẩm theo tên a-z
    public List<Products> findProductsByNameASC(){
        return productRepository.FindByProductNameASC();
    }
    //Hàm filter sản phẩm theo thên z-a
    public List<Products> findProductsByNameDESC(){
        return productRepository.FindByProductNameDESC();
    }
}
