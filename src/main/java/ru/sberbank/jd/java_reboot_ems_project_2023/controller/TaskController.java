package ru.sberbank.jd.java_reboot_ems_project_2023.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.Group;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.Task;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.User;
import ru.sberbank.jd.java_reboot_ems_project_2023.service.GroupService;
import ru.sberbank.jd.java_reboot_ems_project_2023.service.TaskService;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final GroupService groupService;

    @GetMapping("/taskPanel")
    public String taskPanel(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        List<Task> allTaskTeacher = taskService.getAllTaskTeacher((User) currentUser);
        List<Group> allGroup = groupService.getAllGroups();
        model.addAttribute("allGroup", allGroup);
        System.out.println("all task teacher=" + allTaskTeacher);
        model.addAttribute("allTask", allTaskTeacher);
        return "taskPanel";
    }

    @GetMapping("/taskPanelActive")
    public String taskActivePanel(@AuthenticationPrincipal UserDetails currentUser,Model model) {
        List<Task> allTask = taskService.findAll();
        System.out.println("allTask: " + allTask);
        List<Task> activeTask = taskService.findAllActive();
        model.addAttribute("activeTask", activeTask);
        model.addAttribute("allTask", allTask);
        return "taskPanelIsActive";
    }

    @PostMapping("/taskPanel")
    public String createTask(@AuthenticationPrincipal UserDetails currentUser,
                             @Valid @ModelAttribute("task") Task task, BindingResult bindingResult) {
        System.out.println("form create Task=" + task);
        System.out.println("current user=" + currentUser);
        User user = (User) currentUser;
        task.setTeacher(user);
        task.setGroupTask(new Group(1L));
        task.setIsActive(true);
        System.out.println("task setTeacher=" + task);
        taskService.save(task);
        return "redirect:/teacher/taskPanel?";
    }

    @PostMapping("/taskPanel/updateResult")
    public String updateTaskResult(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult,
                                   Boolean result) {
        System.out.println("request update TaskResult=" + result);
        System.out.println("form update Task result=" + task);
        Task taskTemp = taskService.findById(task.getId());
        taskTemp.setResult(result);
        System.out.println("taskTemp=" + taskTemp);
        taskService.update(taskTemp);
        return "redirect:/teacher/taskPanel?";
    }

    @PostMapping("/taskPanel/updateActive")
    public String updateTaskActive(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult,
                                   boolean isActive) {
        System.out.println("request update TaskActive=" + isActive);
        System.out.println("form update Task active=" + task);
        Task taskTemp = taskService.findById(task.getId());
        System.out.println("taskTemp=" + taskTemp);
        taskTemp.setIsActive(isActive);
        System.out.println("taskTemp=" + taskTemp);
        taskService.update(taskTemp);
        return "redirect:/teacher/taskPanel?";
    }

    @PostMapping("/taskPanel/updateTeacher")
    public String updateTaskTeacher(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult) {
        System.out.println("form update Task=" + task);
        Task taskTemp = taskService.findById(task.getId());
        System.out.println("taskTemp=" + taskTemp);
        taskTemp.setTeacher(task.getTeacher());
        System.out.println("taskTemp=" + taskTemp);
        taskService.update(taskTemp);
        return "redirect:/teacher/taskPanel?";
    }

    @PostMapping("/taskPanel/delete")
    public String deleteTask(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult,
                             Model model, RedirectAttributes attributes) {
        System.out.println("form Teacher Delete=" + task);
        Task taskTemp = taskService.findById(task.getId());
        System.out.println("taskTemp=" + taskTemp);
        taskService.delete(task.getId());
        return "redirect:/teacher/taskPanel?";
    }

    @PostMapping("/taskPanel/updateGroup")
    public String updateGroupForTask(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult,
                                     Long idGroup) {
        System.out.println("id group=" + idGroup);
        Task taskTemp = taskService.findById(task.getId());
        taskTemp.setGroupTask(groupService.getGroupById(idGroup));
        taskService.save(taskTemp);
        return "redirect:/teacher/taskPanel?";
    }
}
