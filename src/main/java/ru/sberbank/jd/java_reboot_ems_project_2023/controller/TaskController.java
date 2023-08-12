package ru.sberbank.jd.java_reboot_ems_project_2023.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.Task;
import ru.sberbank.jd.java_reboot_ems_project_2023.service.TaskService;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/taskPanel")
    public String taskPanel(Model model) {
        List<Task> allTask = taskService.findAll();
        model.addAttribute("allTask", allTask);
        return "taskPanel";
    }

    @GetMapping("/taskPanelActive")
    public String taskActivePanel(Model model) {
        List<Task> activeTask = taskService.findAllActive();
        model.addAttribute("activeTask", activeTask);
        return "taskPanelIsActive";
    }

    @PostMapping("/taskPanel")
    public String createTask(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult) {
        System.out.println("form create Task=" + task);
        taskService.save(task);
        return "redirect:/taskPanel";
    }

    @PostMapping("/taskPanel/updateResult")
    public String updateTaskResult(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult) {
        System.out.println("form update Task result=" + task);
        Task taskTemp = taskService.findById(task.getId());
        System.out.println("taskTemp=" + taskTemp);
        taskTemp.setResult(task.getResult());
        System.out.println("taskTemp=" + taskTemp);
        taskService.update(taskTemp);
        return "redirect:/admin/taskPanel";
    }

    @PostMapping("/taskPanel/updateActive")
    public String updateTaskActive(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult) {
        System.out.println("form update Task active=" + task);
        Task taskTemp = taskService.findById(task.getId());
        System.out.println("taskTemp=" + taskTemp);
        taskTemp.setIsActive(task.getIsActive());
        System.out.println("taskTemp=" + taskTemp);
        taskService.update(taskTemp);
        return "redirect:/admin/taskPanel";
    }

    @PostMapping("/taskPanel/updateTeacher")
    public String updateTaskTeacher(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult) {
        System.out.println("form update Task=" + task);
        Task taskTemp = taskService.findById(task.getId());
        System.out.println("taskTemp=" + taskTemp);
        taskTemp.setTeacher(task.getTeacher());
        System.out.println("taskTemp=" + taskTemp);
        taskService.update(taskTemp);
        return "redirect:/admin/taskPanel";
    }

    @PostMapping("/taskPanel/delete")
    public String deleteTask(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult,
                                Model model, RedirectAttributes attributes) {
        System.out.println("form Teacher Delete=" + task);


        if (taskService.getTaskById(task.getId()).isPresent()) {
            System.out.println("Task not found");
            model.addAttribute("error", "Task not found");
            return "redirect:/admin/taskPanel";
        }

        taskService.delete(task.getId());
        return "redirect:/admin/taskPanel";
    }
}
