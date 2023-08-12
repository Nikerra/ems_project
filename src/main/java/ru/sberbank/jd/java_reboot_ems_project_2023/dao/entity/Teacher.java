package ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "teacher")
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=2, max=50, message = "Не меньше 2 знаков")
    private String name;

    @NotBlank
    @Size(min=2, max=50, message = "Не меньше 2 знаков")
    private String password;

    @Email
    private String email;

    @NotNull
    private String role;

    public void setRole() {
        this.role = "ROLE_ADMIN";
    }
}
