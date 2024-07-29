package sc.spring.natsListener.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sc.spring.natsListener.entities.User;
import sc.spring.natsListener.services.UserService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final UserService userService;

    @KafkaListener(topics = "user", groupId = "a")
    public void listen(User user) {
        userService.addUser(user);

    }

    @KafkaListener(topics = "transaction", groupId = "a")
    @Transactional
    public void transactionalListen(User user){
        log.info("received message: {}", user);

        userService.addUser(user);
    }

    @KafkaListener(topics = "batch", groupId = "a")
    public void batchListen(List<User> users){
        log.info("Get messages: {}", users);

        for (User user : users){
            userService.addUser(user);
        }
    }

}
