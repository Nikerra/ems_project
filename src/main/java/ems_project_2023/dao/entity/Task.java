package ems_project_2023.dao.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isActive;

    @Size(min=2, max=50, message = "Не меньше 2 знаков")
    private String name;

    @ManyToOne
    @JoinColumn (name="group_id", referencedColumnName = "id")
    private Group groupTask;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private User teacher;
}
