package Services.user;

import Exceptions.AppException;
import Exceptions.ErrorCode;
import Model.Messages;
import Model.Users;
import Repository.user.MessagesRepository;
import Repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetMessageService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessagesRepository messagesRepository;

    public List<Messages> getMessages(int user_id) {
        Users users = userRepository.FindById(user_id);
        if (users == null) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        List<Messages> messages = messagesRepository.findByUserId(user_id);
        if (messages == null) {
            throw new AppException(ErrorCode.NOT_FOUND_MESSAGES);
        }
        return messages;
    }
}
