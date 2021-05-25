package tech.shivam.caloriemanager;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import tech.shivam.caloriemanager.model.Exercise;
import tech.shivam.caloriemanager.model.User;
import tech.shivam.caloriemanager.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "This is to get all the users registered in the database", operationId = "findAllUsers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "Fetched All users from database",
            content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(summary = "This is to find user by their ID", operationId = "findUserById")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",
            description = "Fetch User stored in DB by their ID",
            content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/find/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(summary = "This is to find user by their username", operationId = "findUserByUserName")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",
                    description = "Fetch User stored in DB by their username",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/findBy/{username}")
    public ResponseEntity<User> getUserByUserName(@PathVariable("username") String username) {
        User user = userService.findUserByUserName(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(summary = "This is to add user to the database", operationId = "addUser")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",
                    description = "Add user to the database",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("/add")
    public ResponseEntity<List<Exercise>> addUser(@RequestBody User user) {
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser.getExercises(), HttpStatus.CREATED);
    }

    @Operation(summary = "This is to update user to the database", operationId = "updateUser")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",
                    description = "Update user to the database",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("/update/user")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @Operation(summary = "This is to delete user to the database", operationId = "deleteById")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",
                    description = "Delete user to the database",
                    content = {@Content(mediaType = "application/json")})
    })
    @DeleteMapping("/deleteBy/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
