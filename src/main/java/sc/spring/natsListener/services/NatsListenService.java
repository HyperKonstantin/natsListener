package sc.spring.natsListener.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.*;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sc.spring.natsListener.entities.User;
import sc.spring.natsListener.repositories.UserRepository;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.IntStream;

@Service
@Slf4j
public class NatsListenService {

    private final UserRepository userRepository;
    private Connection natsConnection;
    private Dispatcher natsDispatcher;
    private Subscription subscription;

    private int natsQueryCounter;

    @Getter
    private User currentUser;

    public NatsListenService(UserRepository userRepository, Environment environment) {
        this.userRepository = userRepository;
        natsQueryCounter = 0;

        try {
            natsConnection = Nats.connect(environment.getProperty("values.nats.host"));
            natsDispatcher = natsConnection.createDispatcher();
            } catch (Exception e) {
            log.error("--- Не удалось подключиться к NATS-серверу ---");
            throw new RuntimeException(e);
        }

        subscription = natsDispatcher.subscribe("info.user", this::natsMessageHandler);
    }

    private void natsMessageHandler(Message message) {

        if (message == null) {
            log.warn("Empty message handled!");
            return;
        }

        currentUser = userRepository.findById(ByteArrayToLong(message.getData())).orElseThrow();

        try {
            log.info("{}. GetMessage! JSON: {}", ++natsQueryCounter, (new ObjectMapper()).writeValueAsString(currentUser));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        String responseString = "User " + currentUser.getName() + " was sended successfully!";
        natsConnection.publish(message.getReplyTo(), responseString.getBytes());
    }

    private Long ByteArrayToLong(byte[] bytes){
        StringBuilder number = new StringBuilder();

        for (byte b : bytes){
            number.append((char) b);
        }

        return Long.parseLong(number.toString());
    }
}
