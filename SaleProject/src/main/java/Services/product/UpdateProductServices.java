package Services.product;

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
public class UpdateProductServices {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ImgRepository imgRepository;
    // Hàm up 1 ảnh
    public Images uploadFile(MultipartFile file,Products products) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        Images images = new Images();
        images.setImg_url(uploadResult.get("secure_url").toString());
        images.setPublic_image_url(uploadResult.get("public_id").toString());
        images.setProducts(products);
        imgRepository.save(images);
        return images;
    }
    //uploadResult trả về là một Map<String, Object>, ví dụ khi upload ảnh kết quả có thể là : {
    //  "asset_id": "abc123",
    //  "public_id": "folder/image123",
    //  "version": 1730000000,
    //  "signature": "xyz",
    //  "secure_url": "https://res.cloudinary.com/.../image123.jpg",
    //  ...
    //}

    // Hàm up nhiều ảnh
    public List<Images> uploadMultiImageFiles(MultipartFile[] files,Products products) throws IOException {
        List<Images> imagesList  = new ArrayList<>();
        for(MultipartFile file : files){
            try {
                Images images = uploadFile(file,products);
                imagesList.add(images);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return imagesList;
    }
    public void updateProduct(int product_id, String productName, int category_id, int quantity,
                              String description, Double product_price, MultipartFile[] image) throws IOException {
        Category category = categoryRepository.FindById(category_id);
        Products products = productRepository.FindById(product_id);
        if(products == null){
            throw new AppException(ErrorCode.PRODUCT_NOT_EXISTED);
        }
        products.setProduct_name(productName);
        products.setProduct_price(product_price);
        products.setQuantity(quantity);
        products.setDescription(description);
        products.setCategory(category);
        productRepository.save(products);
        if(image != null && image.length > 0){
            List<Images> oldImages = imgRepository.FindByProductId(product_id);
            for(Images images : oldImages){
                try {
                    if(images.getPublic_image_url() != null){
                        cloudinary.uploader().destroy(images.getPublic_image_url(), ObjectUtils.emptyMap());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            imgRepository.deleteAll(oldImages);
            uploadMultiImageFiles(image,products);
        }

    }

}
