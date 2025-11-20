package Repository.product;

import Model.Shopping_Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<Shopping_Cart, Integer> {
    @Query(value = "select * from Shopping_Cart where user_id = :user_id",nativeQuery = true)
    Shopping_Cart FindByUser_Id(@Param("user_id")int user_id);
    @Query(value = "select * from Shopping_Cart where user_id = :user_id",nativeQuery = true)
    List<Shopping_Cart> FindListByUser_Id(@Param("user_id")int user_id);
}
