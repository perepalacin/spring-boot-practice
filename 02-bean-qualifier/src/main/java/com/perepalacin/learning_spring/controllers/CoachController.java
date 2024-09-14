package com.perepalacin.learning_spring.controllers;

import com.perepalacin.learning_spring.models.Coach;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoachController {

    private final Coach coach;

    public CoachController(@Qualifier("basketCoach") Coach coach) {
        this.coach = coach;
    }

    @GetMapping("/daily-workout")
    public String getDailyWorkout() {
        return coach.getDailyWorkout();
    }
}
