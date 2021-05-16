package tech.shivam.caloriemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.shivam.caloriemanager.exceptions.ExerciseNotFoundException;
import tech.shivam.caloriemanager.exceptions.UserNotFoundException;
import tech.shivam.caloriemanager.model.Exercise;
import tech.shivam.caloriemanager.model.User;
import tech.shivam.caloriemanager.repo.ExerciseRepo;
import tech.shivam.caloriemanager.repo.UserRepo;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@Service
public class ExerciseService {
    private final ExerciseRepo exerciseRepo;
    private final UserRepo userRepo;
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    public ExerciseService(ExerciseRepo exerciseRepo, UserRepo userRepo) {
        this.exerciseRepo = exerciseRepo;
        this.userRepo = userRepo;
    }

    public Exercise addExercise(Exercise exercise, Long user_id) {
        User user = userRepo.findUserById(user_id).orElseThrow(() -> new UserNotFoundException("user not found by this id"));
        double burntCalories = calculateCalorie(user, exercise);
        user.setTotalCalories(user.getTotalCalories() + burntCalories);
        exercise.setExerciseCode(UUID.randomUUID().toString());
        exercise.setUser(user);
        exercise.setCaloriesBurnt(burntCalories);
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        exercise.setRunTime(date);
        return exerciseRepo.save(exercise);
    }

    public List<Exercise> findAllExercises(String username) {
        User user = userRepo.findUserByUsername(username).orElseThrow(() -> new UserNotFoundException("user not found by this id"));
        return exerciseRepo.findExerciseByUser(user);
    }

    public Exercise findExerciseById(Long id) {
        return exerciseRepo.findExerciseById(id).orElseThrow(() -> new ExerciseNotFoundException("exercise not found"));
    }

    public List<Exercise> findExerciseByRunTimeBetween(java.sql.Date date1, java.sql.Date date2) {
        return exerciseRepo.findExerciseByRunTimeBetween(date1, date2)
                .orElseThrow(() -> new ExerciseNotFoundException("No exercises between these dates"));
    }

    public void deleteById(Long id) {
        Exercise exercise = findExerciseById(id);
        User user = exercise.getUser();
        user.setTotalCalories(user.getTotalCalories() - exercise.getCaloriesBurnt());
        exerciseRepo.deleteById(id);
    }

    // Harris-Benedict Formula
    private double calculateCalorie(User user, Exercise exercise) {
        double ans = 66.5 + (13.8 * user.getWeight()) + (5 * user.getHeight());
        ans /= (6.8 * user.getAge());
        ans *= (exercise.getTime() > 1200 ? 1.2 : 1.4);
        return ans;
    }

}
