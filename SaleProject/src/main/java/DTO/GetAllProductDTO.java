package DTO;

import java.util.List;

public class GetAllProductDTO {

    private int product_id;
    private String product_name;
    private String description;
    private Double product_price;
    private String category;
    private List<ImagesDTO> images;

    public GetAllProductDTO(int product_id, String product_name, String description, Double product_price, String category, List<ImagesDTO> images) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.description = description;
        this.product_price = product_price;
        this.category = category;
        this.images = images;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Double product_price) {
        this.product_price = product_price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<ImagesDTO> getImages() {
        return images;
    }

    public void setImages(List<ImagesDTO> images) {
        this.images = images;
    }
}
