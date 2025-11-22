package Services.product;

import DTO.CreateProductDTO;
import Exceptions.AppException;
import Exceptions.ErrorCode;
import Model.Category;
import Model.Images;
import Model.Products;
import Repository.product.CategoryRepository;
import Repository.product.ImgRepository;
import Repository.product.ProductRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AddNewProductServices {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private ImgRepository imgRepository;
    // Hàm up 1 ảnh
    public String uploadFile(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return uploadResult.get("secure_url").toString();
    }
    // Hàm up nhiều ảnh
    public List<String> uploadMultiImageFiles(MultipartFile[] files){
            List<String> urls = new ArrayList<>();
            for(MultipartFile file : files){
                try {
                    String url = uploadFile(file);
                    urls.add(url);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        return urls;
    }
    // Hàm thêm sản phẩm mới
    public void AddNewProduct(CreateProductDTO createProductDTO,MultipartFile[] images) {
        Category category = categoryRepository.FindById(createProductDTO.getCategory_id());
        Products products = new Products();
        products.setProduct_name(createProductDTO.getProduct_name());
        products.setQuantity(createProductDTO.getQuantity());
        products.setDescription(createProductDTO.getDescription());
        products.setCategory(category);
        products.setProduct_price(createProductDTO.getCategory_price());
        productRepository.save(products);
        if (images != null && images.length >= 4) {
            List<String> imageUrls = uploadMultiImageFiles(images);
            imageUrls.forEach(url -> {
                Images img = new Images();
                img.setImg_url(url);
                img.setProducts(products);
                imgRepository.save(img);
            });
        }else{
            throw new AppException(ErrorCode.PRODUCT_IMAGES_NOT_ENOUGH);
        }
    }
}
