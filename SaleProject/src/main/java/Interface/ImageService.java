package Interface;

import Model.Images;
import Model.Products;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    Images uploadFile(MultipartFile file, Products products) throws IOException;
    List<Images> uploadMultiImageFiles(MultipartFile[] files, Products products);
}
