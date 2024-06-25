package sc.spring.natsListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class NatsListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NatsListenerApplication.class, args);
	}

}
