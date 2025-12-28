package Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "content")
    private String content;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Users sender;
    @ManyToOne
    @JoinColumn(name = "reciever_id")
    private Users reciever;
    @Column(name = "create_at")
    private LocalDateTime createAt;

    public Messages() {
    }

    public Messages(String content, Users sender, Users reciever, LocalDateTime createAt) {
        this.content = content;
        this.sender = sender;
        this.reciever = reciever;
        this.createAt = createAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Users getSender() {
        return sender;
    }

    public void setSender(Users sender) {
        this.sender = sender;
    }

    public Users getReciever() {
        return reciever;
    }

    public void setReciever(Users reciever) {
        this.reciever = reciever;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}
