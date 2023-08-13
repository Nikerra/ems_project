package ems_project_2023.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    /**
     *
     * @param currentUser type springframework.security.core.userdetails.UserDetails
     * @param model type springframework.ui.Model
     * @return html page
     */
    @GetMapping
    public String workTeacher(@AuthenticationPrincipal UserDetails currentUser, Model model) {

        model.addAttribute("currentUser", currentUser);
        return "workTeacher";
    }
}
