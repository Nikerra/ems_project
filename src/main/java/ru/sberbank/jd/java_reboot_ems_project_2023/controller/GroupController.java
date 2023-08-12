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
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.Group;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.Task;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.User;
import ru.sberbank.jd.java_reboot_ems_project_2023.service.GroupService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

        @GetMapping("/groupPanel")
        public String groupPanel(@AuthenticationPrincipal UserDetails currentUser,Model model) {
            System.out.println("currentUser information full=" + currentUser);
            User user = (User) currentUser;
            List<Group> groups = null;
            if (user.getRole().getName().equals("ROLE_TEACHER")) {
                groups = groupService.getAllGroupsTeacher(user);
            }
            System.out.println("all groups=" + groups);
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("allGroup", groups);
            return "groupPanel";
        }

//    @GetMapping("/taskPanelActive")
//    public String taskActivePanel(Model model) {
//        List<Task> activeTask = taskService.findAllActive();
//        model.addAttribute("activeTask", activeTask);
//        return "taskPanelIsActive";
//    }
//
    @PostMapping("/groupPanel")
    public String createTask(@Valid @ModelAttribute("group")Group group, BindingResult bindingResult) {
        System.out.println("form create Group=" + group);
        groupService.save(group);
        return "groupPanel";
    }
//
//    @PostMapping("/taskPanel/updateResult")
//    public String updateTaskResult(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult) {
//        System.out.println("form update Task result=" + task);
//        Task taskTemp = taskService.findById(task.getId());
//        System.out.println("taskTemp=" + taskTemp);
//        taskTemp.setResult(task.getResult());
//        System.out.println("taskTemp=" + taskTemp);
//        taskService.update(taskTemp);
//        return "redirect:/admin/taskPanel";
//    }
//
//    @PostMapping("/taskPanel/updateActive")
//    public String updateTaskActive(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult) {
//        System.out.println("form update Task active=" + task);
//        Task taskTemp = taskService.findById(task.getId());
//        System.out.println("taskTemp=" + taskTemp);
//        taskTemp.setIsActive(task.getIsActive());
//        System.out.println("taskTemp=" + taskTemp);
//        taskService.update(taskTemp);
//        return "redirect:/admin/taskPanel";
//    }
//
//    @PostMapping("/taskPanel/updateTeacher")
//    public String updateTaskTeacher(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult) {
//        System.out.println("form update Task=" + task);
//        Task taskTemp = taskService.findById(task.getId());
//        System.out.println("taskTemp=" + taskTemp);
//        taskTemp.setTeacher(task.getTeacher());
//        System.out.println("taskTemp=" + taskTemp);
//        taskService.update(taskTemp);
//        return "redirect:/admin/taskPanel";
//    }
//
//    @PostMapping("/taskPanel/delete")
//    public String deleteTask(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult,
//                             Model model, RedirectAttributes attributes) {
//        System.out.println("form Teacher Delete=" + task);
//
//
//        if (taskService.getTaskById(task.getId()).isPresent()) {
//            System.out.println("Task not found");
//            model.addAttribute("error", "Task not found");
//            return "redirect:/admin/taskPanel";
//        }
//
//        taskService.delete(task.getId());
//        return "redirect:/admin/taskPanel";
//    }
}
