package com.pdev.clientsdemo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity @Getter @Setter @EqualsAndHashCode @ToString @RequiredArgsConstructor
@Table(name = "clients")
public class Client {

    @Id @GeneratedValue( strategy = GenerationType.AUTO )
    public Long id;

    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private Long docPass;
}
