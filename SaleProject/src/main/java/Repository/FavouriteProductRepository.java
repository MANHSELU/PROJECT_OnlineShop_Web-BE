package Repository;

import Model.Favourite_Products;
import Model.Products;
import Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FavouriteProductRepository extends JpaRepository<Favourite_Products, Integer> {
    @Query(value = "select * from Favourite_Products where user_id = :user_id",nativeQuery = true)
    Favourite_Products FindByUserId(@Param("user_id") int  user_id);
}
