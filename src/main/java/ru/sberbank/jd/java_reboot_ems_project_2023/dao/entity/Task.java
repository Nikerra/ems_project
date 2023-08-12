package ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
//@NoArgsConstructor
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=2, max=50, message = "Не меньше 2 знаков")
    private String name;
    private Boolean result;

    @ManyToOne
    @JoinColumn(name = "TEACHER_ID", referencedColumnName = "id", nullable = false)
    private User teacher;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name="GROUP_ID", referencedColumnName = "id", nullable = false)
    private Group groupTask;

    private Boolean isActive;

    public Task(String name, Boolean result, User teacher, Group groupTask, Boolean isActive) {

        this.name = name;
        this.result = result;
        this.teacher = teacher;
        this.groupTask = groupTask;
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Task task = (Task) o;
        return id != null && Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
