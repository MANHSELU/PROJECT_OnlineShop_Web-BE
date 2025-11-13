package Repository.product;

import Model.Favourite_Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FavouriteProductRepository extends JpaRepository<Favourite_Products, Integer> {
    @Query(value = "select * from Favourite_Products where user_id = :user_id and product_id = :product_id ",nativeQuery = true)
    Favourite_Products FindByUserIdAndProductId(@Param("user_id") int  user_id,@Param("product_id") int product_id);
}

