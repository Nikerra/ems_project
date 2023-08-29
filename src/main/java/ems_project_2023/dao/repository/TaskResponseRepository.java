package ems_project_2023.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ems_project_2023.dao.entity.Task;
import ems_project_2023.dao.entity.TaskResponse;
import ems_project_2023.dao.entity.User;

import java.util.List;

public interface TaskResponseRepository extends JpaRepository<TaskResponse, Long> {
    TaskResponse findUserById(Long id);
    TaskResponse findTaskResponseByTaskAndUser(Task task, User currentUser);
    List<TaskResponse> findAllByUserId(Long id);

    List<TaskResponse> findAllByResultNull();

    List<TaskResponse> findAllByResultNullOrResultFalseAndUserId(Long id);

    List<TaskResponse> findAllTaskResponseByTaskIdIn(List<Long> taskIds);

    @Query("SELECT tr FROM TaskResponse tr WHERE tr.task.id IN :taskIds AND tr.result IS NULL")
    List<TaskResponse> findAllTaskResponseByTaskIdInAndResultIsNull(List<Long> taskIds);

    @Query("SELECT tr FROM TaskResponse tr WHERE tr.task.id IN :taskIds AND tr.result IS NOT NULL")
    List<TaskResponse> findAllTaskResponseByTaskIdInAndResultIsNotNull(List<Long> taskIds);

    List<TaskResponse> findAllByUserIdAndResultTrue(Long id);
    List<TaskResponse> findAllByUserIdAndResultFalse(Long id);

    List<TaskResponse> findAllTaskResponseByTaskId(Long id);
}
