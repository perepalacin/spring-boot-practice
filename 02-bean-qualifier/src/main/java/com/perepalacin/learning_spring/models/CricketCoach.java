package com.perepalacin.learning_spring.models;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
//@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CricketCoach implements Coach {

//    @PostConstruct
//    public void postConstruct() {
//        System.out.println("This prototype bean has been built");
//    }
//
//    @PreDestroy
//    public void whateverName() {
//        System.out.println("This bean is about to be destroyed.");
//    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }
}
