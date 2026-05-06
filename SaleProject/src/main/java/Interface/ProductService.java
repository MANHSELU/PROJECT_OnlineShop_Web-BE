package Interface;

import DTO.CreateProductDTO;
import DTO.GetAllProductDTO;
import DTO.GetDetailProductDTO;
import DTO.GetTopProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
     List<GetTopProductDTO> getTopProducts();
     void createProduct(CreateProductDTO createProductDTO, MultipartFile[] images);
     GetDetailProductDTO getDetailProduct(int product_id);
     List<GetAllProductDTO> getAllProducts();
     List<GetAllProductDTO> getAllProductsByCategory(int categoryId);
}
