package tech.shivam.caloriemanager.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
public class Exercise implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private int speed; // in m/s
    private int time; // in sec
    @Column(name = "run_time")
    private java.sql.Date runTime;
    @Column(nullable = false, updatable = false)
    private String exerciseCode;
    private double caloriesBurnt = 0;
    //A single exercise can only belong to one user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Exercise() {
    }

    public Exercise(Long id, int speed, int time, Date runTime) {
        this.id = id;
        this.speed = speed;
        this.time = time;
        this.runTime = runTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Date getRunTime() {
        return runTime;
    }

    public void setRunTime(Date runTime) {
        this.runTime = runTime;
    }

    public String getExerciseCode() {
        return exerciseCode;
    }

    public void setExerciseCode(String exerciseCode) {
        this.exerciseCode = exerciseCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getCaloriesBurnt() {
        return caloriesBurnt;
    }

    public void setCaloriesBurnt(double caloriesBurnt) {
        this.caloriesBurnt = caloriesBurnt;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", speed='" + speed + '\'' +
                ", time='" + time + '\'' +
                ", runTime='" + runTime + '\'' +
                ". exerciseCode='" + exerciseCode + '\'' +
                '}';
    }
}
