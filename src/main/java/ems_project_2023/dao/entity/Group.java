package ems_project_2023.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Entity
@Data
@RequiredArgsConstructor
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

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

    public Group(long id) {
        this.id = id;
    }
}