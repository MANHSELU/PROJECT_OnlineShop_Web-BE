package Repository.product;

import Model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {
    @Query(value = "select * from Products where product_id = :product_id",nativeQuery = true)
    Products FindById(@Param("product_id") int  product_id);
    @Query(value = "select * from Products",nativeQuery = true)
    List<Products> FindAllProducts();
    @Query(value = "select * from Products where product_id = :product_id",nativeQuery = true)
    List<Products> FindListById(@Param("product_id") int  product_id);
    @Query(value = "select * from Products where lower(product_name) LIKE lower(concat('%', :productName, '%'))",nativeQuery = true)
    List<Products> FindByProductName(@RequestParam("productName") String productName);

}
