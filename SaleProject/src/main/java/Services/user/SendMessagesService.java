package Services.user;

import DTO.SendMessageDTO;
import Exceptions.AppException;
import Exceptions.ErrorCode;
import Model.Messages;
import Model.Users;
import Repository.user.MessagesRepository;
import Repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class SendMessagesService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessagesRepository messagesRepository;

    public Messages sendMessages(int sender_id,SendMessageDTO sendMessageDTO) {
        Users sender = userRepository.FindById(sender_id);
        if (sender == null) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        Users reciever = userRepository.FindById(sendMessageDTO.getReciever_id());
        if (reciever == null) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        Messages messages = new Messages(sendMessageDTO.getContent()
                , sender
                , reciever
                , sendMessageDTO.getCreateAt()
        );
        messagesRepository.save(messages);
        return messages;
    }
}
