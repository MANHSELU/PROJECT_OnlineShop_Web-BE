package Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int product_id;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(name = "product_name")
    private String product_name;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "description")
    private String description;
    @Column(name = "product_price")
    private Double product_price;
    @OneToMany(mappedBy = "products")
    private List<Images> images;

    public Products() {
    }

    public Products(int product_id, Category category, String product_name, int quantity, String description, Double product_price, List<Images> images) {
        this.product_id = product_id;
        this.category = category;
        this.product_name = product_name;
        this.quantity = quantity;
        this.description = description;
        this.product_price = product_price;
        this.images = images;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public Double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Double product_price) {
        this.product_price = product_price;
    }
}