package Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int order_id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
    @Column(name = "status")
    private Status status;
    @Column(name = "order_Date")
    private LocalDateTime order_Date;
    @Column(name = "total_price")
    private Double total_price;

    public enum Status {
        ACTIVE, PENDING, CANCEL;
    }

    public Order() {
    }

    public Order(int order_id, Users users, List<OrderDetail> orderDetails, Status status, LocalDateTime order_Date, Double total_price) {
        this.order_id = order_id;
        this.users = users;
        this.orderDetails = orderDetails;
        this.status = status;
        this.order_Date = order_Date;
        this.total_price = total_price;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getOrder_Date() {
        return order_Date;
    }

    public void setOrder_Date(LocalDateTime order_Date) {
        this.order_Date = order_Date;
    }

    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }
}
