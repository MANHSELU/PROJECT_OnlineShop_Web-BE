package Model;

import jakarta.persistence.*;

@Entity
public class Favourite_Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int favourite_product_id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products products;
    @Enumerated(EnumType.STRING) // ===> Khi sử dụng enum thì nó sẽ luôn ở dạng số nguyên  theo thứ tự ví dụ guest đứng đầu thi lưu số 0,...
    private Status status;         // ===> Nên phải dùng @Enumerated(EnumType.STRING) để đổi lại dạng String

    public enum Status{
        FAVOURITE,UN_FAVOURITE;
    }
    public Favourite_Products() {
    }

    public Favourite_Products(int favourite_product_id, Users users, Products products) {
        this.favourite_product_id = favourite_product_id;
        this.users = users;
        this.products = products;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getFavourite_product_id() {
        return favourite_product_id;
    }

    public void setFavourite_product_id(int favourite_product_id) {
        this.favourite_product_id = favourite_product_id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }
}
