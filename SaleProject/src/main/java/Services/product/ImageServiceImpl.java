package Services.product;

import Interface.ImageService;
import Model.Images;
import Model.Products;
import Repository.product.ImgRepository;
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
public class ImageServiceImpl implements ImageService {
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private ImgRepository imgRepository;

    @Override
    public Images uploadFile(MultipartFile file, Products products) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        Images images = new Images();
        images.setImg_url(uploadResult.get("secure_url").toString());
        images.setPublic_image_url(uploadResult.get("public_id").toString());
        images.setProducts(products);
        imgRepository.save(images);
        return images;
    }

    @Override
    public List<Images> uploadMultiImageFiles(MultipartFile[] files, Products products) {
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

}
