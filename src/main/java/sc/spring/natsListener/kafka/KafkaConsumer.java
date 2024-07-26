package sc.spring.natsListener.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sc.spring.natsListener.services.UserService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final UserService userService;

    @KafkaListener(topics = "user", groupId = "a")
    public void listen(String message) {
        userService.addUser(message);

    }

    @KafkaListener(topics = "transaction", groupId = "a")
    @Transactional
    public void transactionalListen(String message){
        log.info("received message: {}", message);

        if (!message.equals("sending user")){
            userService.addUser(message);
        }
    }

    @KafkaListener(topics = "batch", groupId = "a", batch = "true")
    public void batchListen(List<String> messages){
        log.info("Get messages: {}", messages);

        for (String message : messages){
            userService.addUser(message);
        }
    }

}
