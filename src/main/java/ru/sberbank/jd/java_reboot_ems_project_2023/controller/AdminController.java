package ru.sberbank.jd.java_reboot_ems_project_2023.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.Group;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.Role;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.Student;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.User;
import ru.sberbank.jd.java_reboot_ems_project_2023.repository.UserRepository;
import ru.sberbank.jd.java_reboot_ems_project_2023.service.GroupService;
import ru.sberbank.jd.java_reboot_ems_project_2023.service.StudentService;
import ru.sberbank.jd.java_reboot_ems_project_2023.service.UserService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final StudentService studentService;
    private final GroupService groupService;
    private  final UserRepository userRepository;

    @GetMapping()
    public String userList(@AuthenticationPrincipal UserDetails currentUser,Model model) {
        model.addAttribute("userForm", new User()); // Добавляем форму для пользователя
        model.addAttribute("allUsers", userService.allUsers());
        model.addAttribute("allRoles",userService.allRoles());
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("student", new Student());
        model.addAttribute("group", new Group());
        model.addAttribute("role", new Role());
        model.addAttribute("currentUser", currentUser);
        return "admin";
    }
    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") Long userId) {
        userService.deleteUser(userId);
        return "redirect:/admin"; // Перенаправляем на страницу списка пользователей после удаления
    }
    @PostMapping("/update")
    public String updateUser( @RequestParam("id") Long userId, @ModelAttribute("id_r") Long roleId) {
        User user =userService.findUserById(userId);
        Role role = userService.findRoleById(roleId);
        user.setRoles(role);
        userService.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "redirect:/admin";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "redirect:/admin";
        }
        if (!userService.saveUser(userForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "redirect:/admin";
        }

        return "redirect:/admin";
    }
}
