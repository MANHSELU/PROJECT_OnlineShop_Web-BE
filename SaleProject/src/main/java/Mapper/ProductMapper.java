package Mapper;

import DTO.GetAllProductDTO;
import DTO.GetDetailProductDTO;
import DTO.GetTopProductDTO;
import Model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    @Autowired
    private ImagesMapper imagesMapper;

    public GetTopProductDTO toTopProductDTO(Products products){
        return new GetTopProductDTO(
                products.getProduct_id(),
                products.getProduct_name(),
                products.getProduct_price(),
                products.getDescription(),
                products.getCategory().getCategory_name(),
                imagesMapper.toImageListDTO(products.getImages())
        );
    }

    public GetDetailProductDTO toProductDetailDTO(Products products){
        return new GetDetailProductDTO(
                products.getProduct_id(),
                products.getProduct_name(),
                products.getProduct_price(),
                products.getDescription(),
                products.getQuantity(),
                products.getCategory().getCategory_name(),
                imagesMapper.toImageListDTO(products.getImages())
        );
    }

    public GetAllProductDTO toAllProductDTO(Products products){
        return new GetAllProductDTO(
                products.getProduct_name(),
                products.getDescription(),
                products.getProduct_price(),
                products.getCategory().getCategory_name(),
                imagesMapper.toImageListDTO(products.getImages())
        );
    }

}

