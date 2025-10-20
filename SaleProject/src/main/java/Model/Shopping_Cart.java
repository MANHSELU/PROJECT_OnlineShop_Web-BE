package Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Shopping_Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cart_id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private Users users;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products products;
    @Column(name = "date_add")
    private LocalDateTime date_add;
    @Column(name = "quantity")
    private int quantity;

    public Shopping_Cart() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
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

    public LocalDateTime getDate_add() {
        return date_add;
    }

    public void setDate_add(LocalDateTime date_add) {
        this.date_add = date_add;
    }
}
