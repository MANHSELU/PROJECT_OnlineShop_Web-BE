package Repository.product;

import Model.Shopping_Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<Shopping_Cart, Integer> {
    @Query(value = "select * from Shopping_Cart where user_id = :user_id and product_id = :product_id",nativeQuery = true)
    Shopping_Cart FindByUser_IdAndProduct_Id(@Param("user_id")int user_id,@Param("product_id") int product_id);

}
