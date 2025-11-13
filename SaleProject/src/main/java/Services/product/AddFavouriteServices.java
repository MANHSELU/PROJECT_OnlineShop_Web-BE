package Services.product;

import Exceptions.AppException;
import Exceptions.ErrorCode;
import Model.Favourite_Products;
import Model.Products;
import Model.Users;
import Repository.product.FavouriteProductRepository;
import Repository.product.ProductRepository;
import Repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddFavouriteServices {
    @Autowired
    private FavouriteProductRepository favouriteProductRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    public void AddFavouriteProduct(int user_id, int product_id){
        Users user = userRepository.FindById(user_id);
        if(user == null){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        Products products = productRepository.FindById(product_id);
        if(products == null){
            throw new AppException(ErrorCode.PRODUCT_NOT_EXISTED);
        }
        Favourite_Products favourite_products = favouriteProductRepository.FindByUserIdAndProductId(user_id,product_id);
        if(favourite_products != null){
            favouriteProductRepository.delete(favourite_products);
        }else{
            Favourite_Products favouriteProducts = new Favourite_Products();
            favouriteProducts.setProducts(products);
            favouriteProducts.setUsers(user);
            favouriteProductRepository.save(favouriteProducts);
        }

    }
}
