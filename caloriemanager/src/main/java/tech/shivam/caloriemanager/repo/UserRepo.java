package tech.shivam.caloriemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.shivam.caloriemanager.model.User;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    void deleteById(Long id);

    Optional<User> findUserById(Long id);

    Optional<User> findUserByUsername(String username);
}
