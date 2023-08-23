package ems_project_2023.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "task_resp")
public class TaskResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String answer;

    private Boolean result;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    public TaskResponse() {}

    public TaskResponse(String answer, Task task, User user) {
        this.answer = answer;
        this.task = task;
        this.user = user;
    }

}
