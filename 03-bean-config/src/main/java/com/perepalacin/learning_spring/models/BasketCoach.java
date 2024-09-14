package com.perepalacin.learning_spring.models;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class BasketCoach implements Coach {

    @Override
    public String getDailyWorkout() {
        return "Practice juggling for 15 minutes";
    }
}
