package Model;

import jakarta.persistence.*;

@Entity
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notification_id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
    @Column(name = "message")
    private String message;
    @Column(name = "send_time")
    private String send_time;

    public Notifications() {
    }

    public Notifications(int notification_id, Users users, String message, String send_time) {
        this.notification_id = notification_id;
        this.users = users;
        this.message = message;
        this.send_time = send_time;
    }

    public int getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(int notification_id) {
        this.notification_id = notification_id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSend_time() {
        return send_time;
    }

    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }
}
