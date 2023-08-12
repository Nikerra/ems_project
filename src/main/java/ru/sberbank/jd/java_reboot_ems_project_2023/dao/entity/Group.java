package ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity
@Data
@RequiredArgsConstructor
//@NoArgsConstructor
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "id", nullable = false)
    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "TASK_ID", referencedColumnName = "id", nullable = false)
//    private Task task;

    @OneToMany(mappedBy="groupUser")
    @ToString.Exclude
    private Set<User> users;

    @OneToMany(mappedBy="groupTask")
    @ToString.Exclude
    private Set<Task> tasks;

    public Group(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Group(String name, Set<Task> tasks) {
        this.name = name;
        this.tasks = tasks;
    }
}