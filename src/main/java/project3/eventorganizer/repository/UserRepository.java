package project3.eventorganizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project3.eventorganizer.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);
}
