package tech.shivam.caloriemanager.exceptions;

public class ExerciseNotFoundException extends RuntimeException {
    public ExerciseNotFoundException(String exercise_not_found) {
        super(exercise_not_found);
    }
}
