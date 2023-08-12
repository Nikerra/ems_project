package ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity;

import lombok.*;

import javax.persistence.*;
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

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "group_student",
            joinColumns = { @JoinColumn(name = "group_id") },
            inverseJoinColumns = { @JoinColumn(name = "student_id") }
    )
    private Set<User> students;

    @OneToMany(mappedBy="groupTask")
    @ToString.Exclude
    private Set<Task> tasks;

    public Group(Long id) {
        this.id = id;
    }
}