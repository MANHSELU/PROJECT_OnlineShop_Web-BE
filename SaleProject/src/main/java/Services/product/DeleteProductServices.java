package Services.product;

import Exceptions.AppException;
import Exceptions.ErrorCode;
import Model.Images;
import Model.Products;
import Repository.product.ImgRepository;
import Repository.product.ProductRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DeleteProductServices {
        @Autowired
        private ProductRepository productRepository;
        @Autowired
        private ImgRepository imgRepository;
        @Autowired
        private Cloudinary cloudinary;
        public void deleteProduct(int product_id) throws IOException {
           Products products = productRepository.FindById(product_id);
           if(products == null){
               throw new AppException(ErrorCode.PRODUCT_NOT_EXISTED);
           }
            List<Images> imagesList = imgRepository.FindByProductId(product_id);
           for(Images images : imagesList){
                   if(images.getPublic_image_url() != null){
                       cloudinary.uploader().destroy(images.getPublic_image_url(), ObjectUtils.emptyMap()); //cloudinary.uploader().destroy(publicId, options)
                       // cloudinary yêu cầu options là 1 map ví dụ : invalidate, ....
                       // trong trường hợp này ko truyền gì vào thì để là mãng rỗng
                       // cloudinary recommend ObjectUtils.emptyMap()
                   }
               }
           imgRepository.deleteAll(imagesList);
           productRepository.delete(products);

        }
}
