package ems_project_2023.dao.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean isActive;
    @Size(min=2, max=50, message = "Не меньше 2 знаков")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name="group_id", referencedColumnName = "id")
    private Group groupTask;

    @ManyToOne
    @JoinColumn(name = "TEACHER_ID", referencedColumnName = "id")
    private User teacher;
}
