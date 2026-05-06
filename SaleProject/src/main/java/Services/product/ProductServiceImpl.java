package Services.product;

import DTO.CreateProductDTO;
import DTO.GetAllProductDTO;
import DTO.GetDetailProductDTO;
import DTO.GetTopProductDTO;
import Exceptions.AppException;
import Exceptions.ErrorCode;
import Interface.ProductService;
import Mapper.ProductMapper;
import Model.Category;
import Model.Products;
import Repository.category.CategoryRepository;
import Repository.product.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ImageServiceImpl imageServiceImpl;

    @Override
    public List<GetTopProductDTO> getTopProducts(){
       List<Products> products = productRepository.FindTopProductsList();
       return products.stream().map(productMapper::toTopProductDTO).toList();
    }

    @Override
    @Transactional
    public void createProduct(CreateProductDTO createProductDTO, MultipartFile[] images) {
        if (images == null || images.length < 4) {
            throw new AppException(ErrorCode.PRODUCT_IMAGES_NOT_ENOUGH);
        }
        Category category = categoryRepository.FindById(createProductDTO.getCategory_id());
        if(category == null){
            throw new AppException(ErrorCode.CATEGORY_NOT_EXISTED);
        }
        Products products = productMapper.toProductEntity(createProductDTO);
        products.setCategory(category);
        productRepository.save(products);
        imageServiceImpl.uploadMultiImageFiles(images, products);

    }

    @Override
    public GetDetailProductDTO getDetailProduct(int product_id) {
        Products product = productRepository.FindById(product_id);
        if (product == null) {
            throw new AppException(ErrorCode.PRODUCT_NOT_EXISTED);
        }
        return productMapper.toProductDetailDTO(product);
    }

    @Override
    public List<GetAllProductDTO> getAllProducts() {
        List<Products> products = productRepository.findAll();
        return products.stream().map(productMapper::toAllProductDTO).toList();
    }

    @Override
    public List<GetAllProductDTO> getAllProductsByCategory(int categoryId) {
        List<Products> products = productRepository.FindByCategory(categoryId);
        return products.stream().map(productMapper::toAllProductDTO).toList();
    }


}
