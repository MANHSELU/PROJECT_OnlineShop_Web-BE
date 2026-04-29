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
    @Query(value = "select * from Products where product_id = :product_id",nativeQuery = true)
    List<Products> FindListById(@Param("product_id") int  product_id);
    @Query(value = "select * from Products where lower(product_name) LIKE lower(concat('%', :productName, '%'))",nativeQuery = true)
    List<Products> FindByProductName(@RequestParam("productName") String productName);
    @Query(value = "select * from Products where category_id = :category_id", nativeQuery = true)
    List<Products> FindByCategory(@Param("category_id") int category_id);
    @Query(value = "select * from Products order by product_price asc",nativeQuery = true)
    List<Products> FindByPriceASC();
    @Query(value = "select * from Products order by product_price desc",nativeQuery = true)
    List<Products> FindByPriceDESC();
    @Query(value = "select * from Products order by product_name asc",nativeQuery = true)
    List<Products> FindByProductNameASC();
    @Query(value = "select * from Products order by product_name desc",nativeQuery = true)
    List<Products> FindByProductNameDESC();
    @Query(value = "select top 5 * from Products", nativeQuery = true)
    List<Products> FindTopProductsList();
}