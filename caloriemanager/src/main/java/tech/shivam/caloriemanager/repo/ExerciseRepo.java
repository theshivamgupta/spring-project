package tech.shivam.caloriemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.shivam.caloriemanager.model.Exercise;
import tech.shivam.caloriemanager.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepo extends JpaRepository<Exercise, Long> {
    Optional<Exercise> findExerciseById(Long id);

    //Optional<List<Exercise>> findExerciseByStartDateBetween(String date1, String date2);date2

    Optional<List<Exercise>> findExerciseByRunTimeBetween(java.sql.Date date1, java.sql.Date date2);

    void deleteById(Long id);

    List<Exercise> findExerciseByUser(User user);
}
