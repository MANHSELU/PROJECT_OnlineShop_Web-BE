package Repository.category;

import Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "select * from Category where category_id = :category_id",nativeQuery = true)
    Category FindById(@Param("category_id") int  category_id);
}
