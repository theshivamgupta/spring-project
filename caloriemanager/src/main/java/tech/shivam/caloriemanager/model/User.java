package tech.shivam.caloriemanager.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

// to avoid Endless sending of response
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "exercises"})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(unique = true)
    private String username;
    private int age;
    private int weight;
    private int height;
    @Column(nullable = false, updatable = false)
    private String userCode;
    private double totalCalories = 0;
    //One user can have many exercise stored
    @OneToMany(mappedBy = "user")
    private List<Exercise> exercises;

    public User() {
    }

    public User(Long id, String username, int age, int weight, int totalCalories) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.weight = weight;
        this.totalCalories = totalCalories;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age='" + age + '\'' +
                ", weight='" + weight + '\'' +
                ". totalCalories='" + totalCalories + '\'' +
                '}';
    }
}
