package DTO;

public class ImagesDTO {
    private int image_id;
    private String public_image_url;
    private String img_url;

    public ImagesDTO(int image_id, String public_image_url, String img_url) {
        this.image_id = image_id;
        this.public_image_url = public_image_url;
        this.img_url = img_url;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getPublic_image_url() {
        return public_image_url;
    }

    public void setPublic_image_url(String public_image_url) {
        this.public_image_url = public_image_url;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
