package ems_project_2023.controller;

import ems_project_2023.dao.entity.Group;
import ems_project_2023.dao.entity.Task;
import ems_project_2023.dao.entity.TaskResponse;
import ems_project_2023.dao.entity.User;
import ems_project_2023.service.GroupService;
import ems_project_2023.service.TaskResponseService;
import ems_project_2023.service.TaskService;
import ems_project_2023.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    private final GroupService groupService;

    private final TaskResponseService taskResponseService;

    private final UserService userService;


    /**
     *
     * @param currentUser type springframework.security.core.userdetails.UserDetails
     * @param model type springframework.ui.Model
     * @return html page
     */
    @GetMapping("/taskPanel")
    public String taskPanel(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        List<Task> allTaskTeacher = taskService.getAllTaskTeacher((User) currentUser);
        List<Group> allGroup = groupService.getAllGroups();
        User user = (User) currentUser;
        model.addAttribute("currentUser", user);
        model.addAttribute("allGroup", allGroup);
        model.addAttribute("allTask", allTaskTeacher);
        return "taskPanel";
    }

    /**
     *
     * @param currentUser  type springframework.security.core.userdetails.UserDetails
     * @param task type dao.entity.Task
     * @param bindingResult type springframework.validation.BindingResult
     * @return html page
     */
    @PostMapping("/taskPanel")
    public String createTask(@AuthenticationPrincipal UserDetails currentUser,
                             @Valid @ModelAttribute("task") Task task, BindingResult bindingResult) {
        User user = (User) currentUser;
        task.setTeacher(user);
        task.setGroupTask(new Group(1L));
        task.setIsActive(true);
        taskService.save(task);

        return "redirect:/teacher/taskPanel?";
    }

    /**
     *
     * @param task type dao.entity.Task
     * @param bindingResult type springframework.validation.BindingResult
     * @param result type Boolean
     * @return html page
     */
    @PostMapping("/taskPanel/updateResult")
    public String updateTaskResult(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult,
                                   Boolean result) {
        Task taskTemp = taskService.findById(task.getId());
        taskService.update(taskTemp);
        return "redirect:/teacher/taskPanel?";
    }

    /**
     *
     * @param task type dao.entity.Task
     * @param bindingResult type springframework.validation.BindingResult
     * @param isActive type Boolean
     * @return html page
     */
    @PostMapping("/taskPanel/updateActive")
    public String updateTaskActive(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult,
                                   boolean isActive) {
        Task taskTemp = taskService.findById(task.getId());
        taskTemp.setIsActive(isActive);
        taskService.update(taskTemp);
        return "redirect:/teacher/taskPanel?";
    }



    /**
     *
     * @param currentUser type springframework.security.core.userdetails.UserDetails
     * @param model type springframework.ui.Model
     * @return html page
     */
    @GetMapping("/taskPanelIsActive")
    public String taskActivePanel(@AuthenticationPrincipal UserDetails currentUser,Model model) {
        List<Task> allTask = taskService.findAll();
        List<Task> activeTask = taskService.findAllActive();
        List<TaskResponse> taskResponse =taskResponseService.getAllTasks();
        List<User> allTeacher = userService.getUsersRoleTeacher();

        model.addAttribute("allTeacher", allTeacher);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("activeTask", activeTask);
        model.addAttribute("allTask", allTask);
        model.addAttribute("taskResponse", taskResponse);
        return "taskPanelIsActive";
    }

    /**
     *
     * @param task type dao.entity.Task
     * @param bindingResult type springframework.validation.BindingResult
     * @return html page
     */
    @PostMapping("/taskPanelIsActive/updateTeacher")
    public String updateTaskTeacher(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult) {
        Task taskTemp = taskService.findById(task.getId());
        taskTemp.setTeacher(task.getTeacher());
        taskService.update(taskTemp);
        return "redirect:/teacher/taskPanelIsActive?";
    }

    /**
     *
     * @param task type dao.entity.Task
     * @param bindingResult type springframework.validation.BindingResult
     * @return html page
     */
    @PostMapping("/taskPanel/delete")
    public String deleteTask(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult) {
        Task taskTemp = taskService.findById(task.getId());
        taskService.delete(taskTemp.getId());
        return "redirect:/teacher/taskPanel?";
    }

    /**
     *
     * @param task type dao.entity.Task
     * @param bindingResult type springframework.validation.BindingResult
     * @param idGroup type Long
     * @return html page
     */
    @PostMapping("/taskPanel/updateGroup")
    public String updateGroupForTask(@AuthenticationPrincipal UserDetails currentUser,
                                     @Valid @ModelAttribute("task") Task task, BindingResult bindingResult,
                                     Long idGroup) {
        Task taskTemp = taskService.findById(task.getId());
        taskTemp.setGroupTask(groupService.getGroupById(idGroup));
        taskService.save(taskTemp);
        return "redirect:/teacher/taskPanel?";
    }

    /**
     *
     * @param currentUser type springframework.security.core.userdetails.UserDetails
     * @param model type springframework.ui.Model
     * @return html page
     */
    @GetMapping("/tasksToCheck")
    public String tasksToCheck(@AuthenticationPrincipal UserDetails currentUser,Model model) {
        List<Task> allTask = taskService.findAll();
        List<Task> activeTask = taskService.findAllActive();
        List<TaskResponse> taskResponse =taskResponseService.getAllTasks();
//        User user = (User) currentUser;

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("activeTask", activeTask);
        model.addAttribute("allTask", allTask);
        model.addAttribute("taskResponse", taskResponse);
        return "tasksToCheck";
    }

    /**
     *
     * @param taskResp type Boolean
     * @param id type Long
     * @return html page
     */
    @PostMapping("/tasksToCheck")
    public String tasksToCheck(@ModelAttribute("res_id") Boolean taskResp , @RequestParam Long id, Model model) {
        TaskResponse taskResponse = taskResponseService.findByTaskId(id);
        taskResponse.setResult(taskResp);
        model.addAttribute("taskResponseResultNull", taskResponse.getUser().getUsername());
        taskResponseService.save(taskResponse);
        return "redirect:/teacher/tasksToCheck";
    }
}
