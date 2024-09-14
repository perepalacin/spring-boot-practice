package com.perepalacin.learning_spring.models;

import org.springframework.stereotype.Component;

@Component
public class HandBallCoach implements Coach {

    @Override
    public String getDailyWorkout() {
        return "Practice passing for 15 minutes";
    }
}
