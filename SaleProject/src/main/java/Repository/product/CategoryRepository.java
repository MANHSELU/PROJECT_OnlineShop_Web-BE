package Repository.product;

import Model.Category;
import Model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "select * from Category where category_id = :category_id",nativeQuery = true)
    Category FindById(@Param("category_id") int  category_id);

}
