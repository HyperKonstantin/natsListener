package sc.spring.natsListener.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sc.spring.natsListener.entities.User;
import sc.spring.natsListener.repositories.MongoUserRepository;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final MongoUserRepository userRepository;
    private int queryCounter;

    @SneakyThrows
    public void addUser(String userJSON) {
        User user = (new ObjectMapper()).readValue(userJSON, User.class);
        userRepository.save(user);
        log.info("{}. GetMessage: {}", ++queryCounter, user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
