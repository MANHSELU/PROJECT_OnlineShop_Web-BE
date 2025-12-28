package Controller.user;

import Model.Messages;
import Model.Users;
import Repository.user.UserRepository;
import Services.user.GetMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GetMessagesController {
    @Autowired
    private GetMessageService getMessageService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getMessage")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<?> getMessage(Authentication authentication) {
        try {
            String email = (String) authentication.getPrincipal();
            Users user = userRepository.FindByEmail(email);
            List<Messages> messages = getMessageService.getMessages(user.getUser_id());
            return ResponseEntity.ok(messages);
        }catch (Exception e){
            return ResponseEntity.ok().body(Map.of("message", e.getMessage()));
        }
    }
}
