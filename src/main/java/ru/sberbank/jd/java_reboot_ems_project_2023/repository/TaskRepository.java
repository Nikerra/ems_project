package ru.sberbank.jd.java_reboot_ems_project_2023.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.Task;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.User;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    public Task findTaskById(Long id);
    List<Task> findAllTaskByIsActiveTrue();
    List<Task> findAllByTeacher(User user);
}
