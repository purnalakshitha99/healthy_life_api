package lk.purna.healthy_life.repository;

import lk.purna.healthy_life.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByEmail(String email);
}
