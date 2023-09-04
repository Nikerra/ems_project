package ems_project_2023.service;

import ems_project_2023.dao.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ems_project_2023.dao.entity.Task;
import ems_project_2023.dao.entity.TaskResponse;
import ems_project_2023.dao.entity.User;
import ems_project_2023.dao.repository.TaskResponseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskResponseRepository taskResponseRepository;

    /**
     *
     * @param task type dao.entity.Task
     * @return dao.entity.Task
     */
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    /**
     *
     * @param task type dao.entity.Task
     * @return dao.entity.Task
     */
    public Task update(Task task) {return save(task);}

    /**
     *
     * @param id type Long
     */
    public void delete(Long id) {
        List<TaskResponse> taskResponse = taskResponseRepository.findAllTaskResponseByTaskId(id);
        if (taskResponse != null) {
            taskResponseRepository.deleteAll(taskResponse);
        }
        taskRepository.deleteById(id);
    }

    /**
     *
     * @return List<Task>
     */
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    /**
     *
     * @return List<Task>
     */
    public List<Task> findAllActive() {
        return taskRepository.findAllTaskByIsActiveTrue();
    }

    /**
     *
     * @return List<Task>
     */
    public List<Task> findAllActiveAndGroup(Long id) {
        return taskRepository.findAllTaskByIsActiveTrueAndGroupTaskId(id);    }

    /**
     *
     * @param id type Long
     * @return dao.entity.Task
     */
    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow();
    }

    /**
     *
     * @param user dao.entity.User
     * @return List<Task>
     */
    public List<Task> getAllTaskTeacher(User user) {
        return taskRepository.findAllByTeacher(user);
    }

    public List<Task> findByUserId(Long id) {
        return taskRepository.findTaskByTeacherId(id);
    }
}
