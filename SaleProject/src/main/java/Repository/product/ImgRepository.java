package Repository.product;

import Model.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImgRepository extends JpaRepository<Images,Integer> {
    @Query(value = "select * from Images where product_id = :product_id", nativeQuery = true)
    List<Images> FindByProductId(@Param("product_id") int product_id);
}
