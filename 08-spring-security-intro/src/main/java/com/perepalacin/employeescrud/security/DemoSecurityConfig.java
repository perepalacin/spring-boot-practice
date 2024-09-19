package com.perepalacin.employeescrud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager () {
//        UserDetails john = User.builder()
//                .username("john")
//                .password("{noop}test123")
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails mary = User.builder()
//                .username("mary")
//                .password("{noop}test123")
//                .roles("EMPLOYEE", "MANAGER")
//                .build();
//
//        UserDetails susan = User.builder()
//                .username("susan")
//                .password("{noop}test123")
//                .roles("EMPLOYEE", "MANAGER", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(john, mary, susan);
//    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        //SOLUTION 1: Use jdbc default schema
        //        //We use the built in jdbc db scheme to run this all by default!
        //        return new JdbcUserDetailsManager(dataSource);
        //Solution 2: Use custom schema
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "SELECT user_id, pw, active FROM members WHERE user_id = ?"
        );

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT user_id, role FROM roles WHERE user_id = ?"
        );

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/v1/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/v1/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/v1/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/employees/**").hasRole("ADMIN")
        );
        http.httpBasic(Customizer.withDefaults());
        //Disable CSRF protocol
        http.csrf(csfr -> csfr.disable());

        return http.build();

    }
}
