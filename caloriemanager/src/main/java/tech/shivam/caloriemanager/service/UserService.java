package tech.shivam.caloriemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.shivam.caloriemanager.exceptions.UserNotFoundException;
import tech.shivam.caloriemanager.model.User;
import tech.shivam.caloriemanager.repo.ExerciseRepo;
import tech.shivam.caloriemanager.repo.UserRepo;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final ExerciseRepo exerciseRepo;
    private final ExerciseService exerciseService;
    @Autowired
    public UserService(UserRepo userRepo, ExerciseRepo exerciseRepo, ExerciseService exerciseService) {
        this.userRepo = userRepo;
        this.exerciseRepo = exerciseRepo;
        this.exerciseService = exerciseService;
    }

    public User addUser(User user) {
        user.setUserCode(UUID.randomUUID().toString());
        return userRepo.save(user);
    }

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public User findUserById(Long id) {
        return userRepo.findUserById(id).orElseThrow(() -> new UserNotFoundException("user not found by this id"));
    }

    public User findUserByUserName(String username) {
        return userRepo.findUserByUsername(username).orElseThrow(() -> new UserNotFoundException(("user not found from this username")));
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }

    public void deleteById(Long id) {
        userRepo.deleteById(id);
        exerciseService.deleteByUser(id);
    }

}
