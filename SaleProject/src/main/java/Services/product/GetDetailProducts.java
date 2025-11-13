package Services.product;


import Exceptions.AppException;
import Exceptions.ErrorCode;
import Model.Products;
import Repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // Đánh dấu để springboot biết đây là lớp service
public class GetDetailProducts {
    @Autowired
    private ProductRepository productRepository;
    public Products getDetailProducts (int product_id){
        Products product = productRepository.FindById(product_id);
        if(product ==null) {
            throw new AppException(ErrorCode.PRODUCT_NOT_EXISTED);
        }
        return product;
    }
}
