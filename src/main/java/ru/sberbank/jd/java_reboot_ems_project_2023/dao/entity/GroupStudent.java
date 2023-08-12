package ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "groupstudent")
@NoArgsConstructor
public class GroupStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
}
