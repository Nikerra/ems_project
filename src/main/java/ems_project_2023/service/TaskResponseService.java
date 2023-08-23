package ems_project_2023.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ems_project_2023.dao.entity.Task;
import ems_project_2023.dao.entity.TaskResponse;
import ems_project_2023.dao.entity.User;
import ems_project_2023.dao.repository.TaskResponseRepository;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskResponseService {

    private final TaskResponseRepository taskResponseRepository;

    public void save(TaskResponse taskResponse) {taskResponseRepository.save(taskResponse);}

    public List<TaskResponse> getAllTasks() {
        return taskResponseRepository.findAll();
    }

    public TaskResponse getResponseByTaskAndUser(Task task, User currentUser) {
        return taskResponseRepository.findTaskResponseByTaskAndUser(task, currentUser);
    }

    public List<TaskResponse> getAllTasksByUser(Long id) {
        return taskResponseRepository.findAllByUserId(id);
    }

    public TaskResponse findByTaskId(Long id) {
        return taskResponseRepository.getById(id);
    }


    public List<TaskResponse> getTasksByResultNull() {
        return taskResponseRepository.findAllByResultNull();
    }

    public List<TaskResponse> getTasksByResultNullOrFalse(Long id) {
        return taskResponseRepository.findAllByResultNullOrResultFalseAndUserId(id);
    }

    public List<TaskResponse> findAllTaskResponseByTaskIds(List<Task> activeTasks) {
        List<Long> taskIds = activeTasks.stream().map(Task::getId).collect(Collectors.toList());
        System.out.println(taskIds);
        return taskResponseRepository.findAllTaskResponseByTaskIdIn(taskIds);
    }

    public List<TaskResponse> findAllTaskResponseByTaskIdsByResultNull(List<Task> allTaskTeacher) {
        List<Long> taskIds = allTaskTeacher.stream().map(Task::getId).collect(Collectors.toList());
        System.out.println(taskIds);
        return taskResponseRepository.findAllTaskResponseByTaskIdInAndResultIsNull(taskIds);
    }

    public List<TaskResponse> findAllTaskResponseByTaskIdsByResultNotNull(List<Task> allTaskTeacher) {
        List<Long> taskIds = allTaskTeacher.stream().map(Task::getId).collect(Collectors.toList());
        System.out.println(taskIds);
        return taskResponseRepository.findAllTaskResponseByTaskIdInAndResultIsNotNull(taskIds);
    }

    public List<TaskResponse> getAllTasksByUserFalse(Long id) {
        return taskResponseRepository.findAllByUserIdAndResultFalse(id);
    }

    public List<TaskResponse> getAllTasksByUserTrue(Long id) {
        return taskResponseRepository.findAllByUserIdAndResultTrue(id);
    }
}
