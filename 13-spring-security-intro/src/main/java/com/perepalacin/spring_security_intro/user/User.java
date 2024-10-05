package com.perepalacin.spring_security_intro.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User {
    @Id
    @GeneratedValue //by default the strategy is auto
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}

