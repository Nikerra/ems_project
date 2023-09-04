package ems_project_2023.TestService;

import ems_project_2023.service.TaskService;
import ems_project_2023.dao.entity.Group;
import ems_project_2023.dao.entity.Task;
import ems_project_2023.dao.entity.User;
import ems_project_2023.dao.repository.TaskRepository;
import ems_project_2023.dao.repository.TaskResponseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private TaskResponseRepository taskResponseRepository;

    private TaskService taskService;
    Task task = new Task();

    @BeforeEach
    void setUp() {
        taskService = new TaskService(taskRepository,taskResponseRepository);


        task.setId(100L);
        task.setName("Lesson Hibernate");
        task.setIsActive(false);
        task.setTeacher(new User());
        task.setGroupTask(new Group(100L, "Test Group 100"));
    }

    @Test
    public void save() {


        taskService.save(task);
        System.out.println(task);

        assertEquals(100L, task.getId());
        assertEquals(false, task.getIsActive());
        assertEquals("Lesson Hibernate", task.getName());
        assertEquals(new Group(100L, "Test Group 100"), task.getGroupTask());
    }

}

