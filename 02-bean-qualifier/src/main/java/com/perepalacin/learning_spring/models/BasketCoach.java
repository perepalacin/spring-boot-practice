package com.perepalacin.learning_spring.models;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary //This annotation would neglect the effect of the @Qualifier one
//It is best to use qualifier! It is more specific and it has higher priority!
public class BasketCoach implements Coach {

    @Override
    public String getDailyWorkout() {
        return "Practice juggling for 15 minutes";
    }
}
