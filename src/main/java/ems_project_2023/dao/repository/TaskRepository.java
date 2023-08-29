package ems_project_2023.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ems_project_2023.dao.entity.Task;
import ems_project_2023.dao.entity.User;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findTaskById(Long id);
    List<Task> findAllTaskByIsActiveTrue();
    List<Task> findAllByTeacher(User user);
    List<Task> findAllTaskByIsActiveTrueAndGroupTaskId(Long id);
    List<Task> findAllByGroupTaskId(Long Id);
    List<Task> findTaskByTeacherId(Long id);
}
