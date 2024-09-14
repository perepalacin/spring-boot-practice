package com.perepalacin.learning_spring.config;

import com.perepalacin.learning_spring.models.Coach;
import com.perepalacin.learning_spring.models.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {
    @Bean
    public Coach swimCoach() { //it takes the SwimCoach class because it turns the name into camelcase!
        return new SwimCoach();
    }
}
