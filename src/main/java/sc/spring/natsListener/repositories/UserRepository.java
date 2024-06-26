package sc.spring.natsListener.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sc.spring.natsListener.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
