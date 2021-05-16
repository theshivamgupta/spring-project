package tech.shivam.caloriemanager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.shivam.caloriemanager.model.Exercise;
import tech.shivam.caloriemanager.service.ExerciseService;

import java.util.List;

@RestController
@RequestMapping("/exercise")
@CrossOrigin(origins = "*")
public class ExerciseResource {
    private final ExerciseService exerciseService;

    public ExerciseResource(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/all/{username}")
    public ResponseEntity<List<Exercise>> getAllExercises(@PathVariable("username") String username) {
        List<Exercise> exercises = exerciseService.findAllExercises(username);
        return new ResponseEntity<>(exercises, HttpStatus.OK);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable("id") Long id) {
        Exercise exercise = exerciseService.findExerciseById(id);
        return new ResponseEntity<>(exercise, HttpStatus.OK);
    }
    // add exercise from given user_id
    @PostMapping("/add/{id}")
    public ResponseEntity<Exercise> addExercise(@RequestBody Exercise exercise, @PathVariable("id") Long id) {
        Exercise newExercise = exerciseService.addExercise(exercise, id);
        return new ResponseEntity<>(newExercise, HttpStatus.CREATED);
    }
    // get exercise 2 dates
    @GetMapping("/getBetween/{date1}/{date2}")
    public ResponseEntity<List<Exercise>> findExerciseByRunTimeBetween(@PathVariable("date1") java.sql.Date date1, @PathVariable("date2") java.sql.Date date2) {
        List<Exercise> exercises = exerciseService.findExerciseByRunTimeBetween(date1, date2);
        return new ResponseEntity<>(exercises, HttpStatus.OK);
    }

    //delete exercise of given id
    @DeleteMapping("/deleteBy/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> deleteExercise(@PathVariable("id") Long id) {
        exerciseService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
