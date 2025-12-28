package DTO;

import java.time.LocalDateTime;

public class SendMessageDTO {
    private String content;
    private int reciever_id;
    private LocalDateTime createAt = LocalDateTime.now();

    public String getContent() {
        return content;
    }

    public int getReciever_id() {
        return reciever_id;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }
}
