package ru.sberbank.jd.java_reboot_ems_project_2023.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.Task;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.User;
import ru.sberbank.jd.java_reboot_ems_project_2023.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task save(Task task) {
        return taskRepository.save(task);
    }
    public Task update(Task task) {return save(task);}

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public List<Task> findAllActive() {
        List<Task> tasks = taskRepository.findAllTaskByIsActiveTrue();
        System.out.println(tasks);
        return taskRepository.findAllTaskByIsActiveTrue();
    }
    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow();
    }

    public Optional<Object> getTaskById(Long id) {
        return Optional.of(taskRepository.findById(id));
    }

    public List<Task> getAllTaskTeacher(User user) {
        return taskRepository.findAllByTeacher(user);
    }
}
