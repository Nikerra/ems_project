package ru.sberbank.jd.java_reboot_ems_project_2023.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.Group;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.Student;
import ru.sberbank.jd.java_reboot_ems_project_2023.service.GroupService;
import ru.sberbank.jd.java_reboot_ems_project_2023.service.StudentService;

@Controller
@RequestMapping(value = "/student")
@RequiredArgsConstructor
public class NewGroupController {

    private final StudentService studentService;
    private final GroupService groupService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("groups", groupService.getAllGroups());
        model.addAttribute("student", new Student());
        model.addAttribute("group", new Group());
        return "index_new";
    }

    @PostMapping("/createStudent")
    public String createStudent(@ModelAttribute("student") Student student) {
        studentService.createStudent(student);
        return "redirect:/";
    }

//    @PostMapping("/createGroup")
//    public String createGroup(@ModelAttribute("group") Group group) {
//        groupService.createGroup(group);
//        return "redirect:/";
//    }

    @GetMapping("/editStudent/{id}")
    public String editStudent(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        model.addAttribute("groups", groupService.getAllGroups());
        return "editStudent";
    }
    @GetMapping("/editGroup/{id}")
    public String editGroup(@PathVariable Long id, Model model) {
        Group group = groupService.getGroupById(id);
        model.addAttribute("group", group);
//        model.addAttribute("groups", groupService.getAllGroups());
        return "editGroup";
    }
    @PostMapping("/updateStudent/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student updatedStudent) {
        Student student = studentService.getStudentById(id);
        student.setName(updatedStudent.getName());
        student.setEmail(updatedStudent.getEmail());
        student.setGroup(updatedStudent.getGroup());
        studentService.updateStudent(student);
        return "redirect:/";
    }
    @PostMapping("/updateGroup/{id}")
    public String updateGroup(@PathVariable Long id, @ModelAttribute("group") Group updatedGroup) {
        Group group = groupService.getGroupById(id);
        group.setName(updatedGroup.getName());
        groupService.updateGroup(group);
        return "redirect:/";
    }
    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/";
    }
    @GetMapping("/deleteGroup/{id}")
    public String deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return "redirect:/";
    }
}
