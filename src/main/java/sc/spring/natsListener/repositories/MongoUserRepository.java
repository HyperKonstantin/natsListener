package sc.spring.natsListener.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sc.spring.natsListener.entities.User;

@Repository
public interface MongoUserRepository extends MongoRepository<User, Long> {
}
