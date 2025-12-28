package Controller.user;

import DTO.SendMessageDTO;
import Model.Messages;
import Model.Users;
import Repository.user.UserRepository;
import Services.user.SendMessagesService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class SendMessagesController {
    @Autowired
    private SendMessagesService sendMessagesService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/sendMessages")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<?> sendMessages(Authentication authentication, @RequestBody SendMessageDTO sendMessageDTO) {
        try {
            String email = (String) authentication.getPrincipal();
            Users users = userRepository.FindByEmail(email);
            Messages messages = sendMessagesService.sendMessages(users.getUser_id(), sendMessageDTO);
            return ResponseEntity.ok().body(messages);
        } catch (Exception e) {
            return ResponseEntity.ok().body(Map.of("message", e.getMessage()));
        }
    }
}
