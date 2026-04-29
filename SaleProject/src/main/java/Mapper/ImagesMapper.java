package Mapper;

import DTO.ImagesDTO;
import Model.Images;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImagesMapper {
        public ImagesDTO toImageDTO (Images images){
            return new ImagesDTO(
                    images.getImage_id(),
                    images.getPublic_image_url(),
                    images.getImg_url()
            );
        }
        public List<ImagesDTO> toImageListDTO(List<Images> images){
            return images.stream().map(this::toImageDTO).toList();
        }

}
