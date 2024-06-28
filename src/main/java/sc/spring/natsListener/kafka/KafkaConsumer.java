package sc.spring.natsListener.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import sc.spring.natsListener.services.NatsListenService;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final NatsListenService natsListenService;

    @KafkaListener(topics = "user", groupId = "userConsumers")
    public void listen(String message) {
        try {
            natsListenService.updateUser(Integer.parseInt(message));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e){
            log.warn("Uncorrect message: {}. Skipped.", message);
        }

    }
}
