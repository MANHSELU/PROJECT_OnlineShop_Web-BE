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

    public Images uploadFile(MultipartFile file, Products products) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        Images images = new Images();
        images.setImg_url(uploadResult.get("secure_url").toString());
        images.setPublic_image_url(uploadResult.get("public_id").toString());
        images.setProducts(products);
        imgRepository.save(images);
        return images;
    }

    public List<Images> uploadMultiImageFiles(MultipartFile[] files, Products products) throws IOException {
        List<Images> imagesList = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                Images images = uploadFile(file, products);
                imagesList.add(images);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return imagesList;
    }

    public Products AddNewProduct(CreateProductDTO createProductDTO, MultipartFile[] images) throws IOException {
        Category category = categoryRepository.FindById(createProductDTO.getCategory_id());
        Products products = new Products();
        products.setProduct_name(createProductDTO.getProduct_name());
        products.setQuantity(createProductDTO.getQuantity());
        products.setDescription(createProductDTO.getDescription());
        products.setCategory(category);
        products.setProduct_price(createProductDTO.getProduct_price());
        productRepository.save(products);
        if (images != null && images.length >= 4) {
            uploadMultiImageFiles(images, products);
        } else {
            throw new AppException(ErrorCode.PRODUCT_IMAGES_NOT_ENOUGH);
        }
        return products;
    }
}
