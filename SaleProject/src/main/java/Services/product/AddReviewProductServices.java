package Services.product;

import Exceptions.AppException;
import Exceptions.ErrorCode;
import Model.Products;
import Model.Reviews;
import Model.Users;
import Repository.product.ProductRepository;
import Repository.product.ReviewRepository;
import Repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AddReviewProductServices {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    public void addReview(int user_id, int product_id, int rating,String comment){
        Users user = userRepository.FindById(user_id);
        if(user==null){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        Products product = productRepository.FindById(product_id);
        if(product==null){
            throw new AppException(ErrorCode.PRODUCT_NOT_EXISTED);
        }
        if (rating < 1 || rating > 5) {
            throw new AppException(ErrorCode.INVALID_RATING);
        }
        Reviews review = new Reviews();
        review.setUser(user);
        review.setProduct(product);
        review.setRating(rating);
        review.setComment(comment);
        review.setCreateAt(LocalDateTime.now());
        reviewRepository.save(review);
    }
}
