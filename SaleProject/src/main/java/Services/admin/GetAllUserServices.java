package Services.admin;

import Model.Users;
import Repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllUserServices {
    @Autowired
    private UserRepository userRepository;

    public List<Users> findAllUser(){
        return userRepository.FindAll();
    }
}
