package sc.spring.natsListener.services;

import io.nats.client.Connection;
import io.nats.client.Message;
import io.nats.client.Nats;
import io.nats.client.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sc.spring.natsListener.repositories.UserRepository;

import java.io.IOException;

@Service
public class NatsListenService {

    private static final Logger log = LoggerFactory.getLogger(NatsListenService.class);
    private final UserRepository userRepository;
    private Connection natsConnection;
    private Subscription subscription;


    public NatsListenService(UserRepository userRepository) {
        this.userRepository = userRepository;

        try {
            natsConnection = Nats.connect();
        } catch (Exception e) {
            log.error("--- Не удалось подключиться к NATS-серверу ---");
            throw new RuntimeException(e);
        }

        subscription = natsConnection.subscribe("info.user");
    }

    @Scheduled(fixedRate = 5_000)
    public void listenNats() throws InterruptedException {

        Message message = subscription.nextMessage(1_000);

        if (message != null){
            userRepository.saveMessage(new String(message.getData()));
            log.info("GetMessage! ({})", new String(message.getData()));
        }
    }
}
