package sc.spring.natsListener.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sc.spring.natsListener.entities.User;
import sc.spring.natsListener.repositories.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class NatsListenService {

    private final UserRepository userRepository;
    private int queryCounter;

    @Getter
    private User currentUser;


    public void updateUser(int id) throws JsonProcessingException {
        currentUser = userRepository.findById((long) id).orElseThrow();

        log.info("{}. GetMessage! JSON: {}", ++queryCounter, (new ObjectMapper()).writeValueAsString(currentUser));
    }
}
