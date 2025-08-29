package Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipping_id")
    private   int shipping_id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products products;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private int phone;
    @Column(name = "shipping_date")
    private LocalDateTime shipping_date;
    @Column(name = "shipping_unit")
    private String shipping_unit;

    public Shipping() {
    }

    public Shipping(int shipping_id, Users users, Products products, String address, int phone, LocalDateTime shipping_date, String shipping_unit) {
        this.shipping_id = shipping_id;
        this.users = users;
        this.products = products;
        this.address = address;
        this.phone = phone;
        this.shipping_date = shipping_date;
        this.shipping_unit = shipping_unit;
    }

    public int getShipping_id() {
        return shipping_id;
    }

    public void setShipping_id(int shipping_id) {
        this.shipping_id = shipping_id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public LocalDateTime getShipping_date() {
        return shipping_date;
    }

    public void setShipping_date(LocalDateTime shipping_date) {
        this.shipping_date = shipping_date;
    }

    public String getShipping_unit() {
        return shipping_unit;
    }

    public void setShipping_unit(String shipping_unit) {
        this.shipping_unit = shipping_unit;
    }
}
