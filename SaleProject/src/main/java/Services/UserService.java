package Services;

import Exceptions.AppException;
import Exceptions.ErrorCode;
import Model.Products;
import Model.Shopping_Cart;
import Model.Users;
import Repository.ProductRepository;
import Repository.ShoppingCartRepository;
import Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public void addToShoppingCart(int user_id, int productId, int quantity){
        Users users = userRepository.FindById(user_id);
        if(users==null){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        Products products = productRepository.FindById(productId);
        if(products==null) {
            throw new AppException(ErrorCode.PRODUCT_NOT_EXISTED);
        }
        Shopping_Cart existingCart = shoppingCartRepository.FindByUser_IdAndProduct_Id(user_id,productId);
        if (existingCart !=null){
            existingCart.setQuantity(quantity);
            shoppingCartRepository.save(existingCart);
        }else {
            Shopping_Cart shoppingCart = new Shopping_Cart();
            shoppingCart.setUsers(users);
            shoppingCart.setProducts(products);
            shoppingCart.setDate_add(LocalDateTime.now());
            shoppingCart.setQuantity(quantity);
            shoppingCartRepository.save(shoppingCart);
        }
    }

}
