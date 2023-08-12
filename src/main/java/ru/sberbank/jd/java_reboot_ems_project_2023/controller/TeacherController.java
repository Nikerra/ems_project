package ru.sberbank.jd.java_reboot_ems_project_2023.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.User;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @GetMapping("")
    public String workTeacher(@AuthenticationPrincipal UserDetails currentUser, Model model) {

        System.out.println("currentUser information full=" + currentUser);
        User user = (User) currentUser;
        if (user.getRole().getName().equals("ROLE_TEACHER")) {
            System.out.println("Teacher information=" + user.getUsername());
        }
        model.addAttribute("currentUser", currentUser);
        return "workTeacher";
    }
}
