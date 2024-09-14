package com.perepalacin.learning_spring.models;

import org.springframework.context.annotation.Bean;

public class SwimCoach implements Coach{

    public SwimCoach() {
        System.out.println("This class has been constructed: " + getClass().getName());
    }

    @Override
    public String getDailyWorkout() {
        return "Swim 1000 meters as a warm up";
    }
}
