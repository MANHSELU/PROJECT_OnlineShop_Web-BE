package Services.product;

import Exceptions.AppException;
import Exceptions.ErrorCode;
import Model.*;
import Repository.product.OrderDetailRepository;
import Repository.product.OrderRepository;
import Repository.product.ProductRepository;
import Repository.product.ShoppingCartRepository;
import Repository.user.UserRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CheckOutOrderServices {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShoppingCartRepository  shoppingCartRepository;
    public void CheckoutOrder(int user_id,int product_id, Double total_price){
        Users users = userRepository.FindById(user_id);
        if(users == null){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        List<Shopping_Cart> Cart = shoppingCartRepository.FindListByUser_Id(user_id);
        if(Cart == null){
            throw new AppException(ErrorCode.CART_NOT_EXISTED);
        }
        LocalDateTime order_date = LocalDateTime.now();
        Order order = new Order();
        order.setUsers(users);
        order.setStatus(Order.Status.ACTIVE);
        order.setOrder_Date(order_date);
        order.setTotal_price(total_price);
        orderRepository.save(order);
            Products products = productRepository.FindById(product_id);
            if(products == null){
                throw new AppException(ErrorCode.PRODUCT_NOT_EXISTED);
            }
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(products);
            orderDetail.setQuantity(10); // Test API, sau này sửa sau
            orderDetail.setPrice(products.getProduct_price());
            orderDetailRepository.save(orderDetail);

        shoppingCartRepository.deleteAll(Cart);
    }
}
