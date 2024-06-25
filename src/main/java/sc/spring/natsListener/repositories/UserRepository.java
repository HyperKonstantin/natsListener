package sc.spring.natsListener.repositories;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private String LastUserMessage;

    public void saveMessage(String message){
        LastUserMessage = message;
    }

    public String getMessage(){
        return LastUserMessage;
    }

}
