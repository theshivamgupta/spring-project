package tech.shivam.caloriemanager;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "This is to get all exercise instances with respect to username", operationId = "findAllExercises")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched All Exercises from database",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/all/{username}")
    public ResponseEntity<List<Exercise>> getAllExercises(@PathVariable("username") String username) {
        List<Exercise> exercises = exerciseService.findAllExercises(username);
        return new ResponseEntity<>(exercises, HttpStatus.OK);
    }

    @Operation(summary = "This is to fetch exercise by their ID", operationId = "findExerciseById")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched Exercise from database by their ID",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("find/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable("id") Long id) {
        Exercise exercise = exerciseService.findExerciseById(id);
        return new ResponseEntity<>(exercise, HttpStatus.OK);
    }

    // add exercise from given user_id
    @Operation(summary = "This is to add exercise instance to the database by user_id", operationId = "addExercise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Add exercise to the database",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("/add/{id}")
    public ResponseEntity<Exercise> addExercise(@RequestBody Exercise exercise, @PathVariable("id") Long id) {
        Exercise newExercise = exerciseService.addExercise(exercise, id);
        return new ResponseEntity<>(newExercise, HttpStatus.CREATED);
    }
    // get exercise 2 dates
    @Operation(summary = "This is to get all the exercises between specific date", operationId = "findExerciseByRunTimeBetween")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Fetched All exercises instance between following dates",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/getBetween/{date1}/{date2}")
    public ResponseEntity<List<Exercise>> findExerciseByRunTimeBetween(@PathVariable("date1") String date1, @PathVariable("date2") String date2) {
        List<Exercise> exercises = exerciseService.findExerciseByRunTimeBetween(date1, date2);
        return new ResponseEntity<>(exercises, HttpStatus.OK);
    }

    //delete exercise of given id
    @Operation(summary = "This is to delete exercise by their ID", operationId = "deleteById")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Delete Exercise by their ID",
                    content = {@Content(mediaType = "application/json")})
    })
    @DeleteMapping("/deleteBy/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> deleteExercise(@PathVariable("id") Long id) {
        exerciseService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
