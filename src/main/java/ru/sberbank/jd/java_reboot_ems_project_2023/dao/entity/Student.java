package ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Group group;

//    @OneToMany(fetch = FetchType.EAGER)
//    private Set<Task> task;

}
