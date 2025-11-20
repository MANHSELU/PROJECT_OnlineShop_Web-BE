package DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateProductDTO {
    @NotBlank(message = "Product name is required")
    private String product_name;
    @NotBlank(message = "Product name is required")
    @Min(value = 1, message = "Quantity must be > 0")
    private int quantity;
    @NotBlank(message = "Description name is required")
    private String description;
    @NotNull(message = "Category is required")
    private int category_id;
    @NotBlank(message = "Product name is required")
    @Min(value = 1, message = "Price must be > 0")
    private Double category_price;

    public CreateProductDTO(String product_name, int quantity, String description, int category_id, Double category_price) {
        this.product_name = product_name;
        this.quantity = quantity;
        this.description = description;
        this.category_id = category_id;
        this.category_price = category_price;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public Double getCategory_price() {
        return category_price;
    }

    public void setCategory_price(Double category_price) {
        this.category_price = category_price;
    }
}
