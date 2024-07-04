package sc.spring.natsListener.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import sc.spring.natsListener.services.UserService;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final UserService natsListenService;

    @KafkaListener(topics = "user", groupId = "userConsumers")
    public void listen(String message) {
        natsListenService.updateUser(message);
    }
}
