package ems_project_2023.controller;

import ems_project_2023.dao.entity.Task;
import ems_project_2023.dao.entity.TaskResponse;
import ems_project_2023.dao.entity.User;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ems_project_2023.service.TaskResponseService;
import ems_project_2023.service.TaskService;
import java.util.List;

@Controller
@RequestMapping(value = "/student")
@RequiredArgsConstructor
public class StudentController {

    private final TaskService taskService;
    private final TaskResponseService taskResponseService;

    /***
     *
     * @param currentUser authorized user
     * @param model model
     * @return model about the student
     */
    @GetMapping()
    public String viewTasks(@AuthenticationPrincipal UserDetails currentUser,Model model) {
        User user = (User) currentUser;
        List<Task> tasks = taskService.findAllActiveAndGroup(user.getGroupUser().getId());
        List<TaskResponse> allTasksResponse = taskResponseService.getAllTasksByUser(user.getId());
        model.addAttribute("tasks", tasks);
        model.addAttribute("currentUser", user);
        model.addAttribute("allTasksResponse", allTasksResponse);
        return "student";
    }

    /***
     *
     * @param taskId id task
     * @param currentUser authorized user
     * @param answer Answer in task
     * @return model about the student
     */
    @PostMapping("/response")
    public String submitAnswer(@RequestParam("taskId") Long taskId,
                               @AuthenticationPrincipal UserDetails currentUser,
                               @RequestParam("answer") String answer) {
        Task task = taskService.findById(taskId);
        User user = (User) currentUser;
        TaskResponse existingResponse = taskResponseService.getResponseByTaskAndUser(task, user);
        if (existingResponse == null ) {
            TaskResponse taskResponse1 = new TaskResponse(answer, task, user);
            taskResponseService.save(taskResponse1);
        }else {
            if (existingResponse.getResult()== null) {
                taskResponseService.save(existingResponse);
            } else {if (!existingResponse.getResult()){
                existingResponse.setAnswer(answer);
                existingResponse.setResult(null);
                taskResponseService.save(existingResponse);
            }
            }
        }

        return "redirect:/student";
    }
}
