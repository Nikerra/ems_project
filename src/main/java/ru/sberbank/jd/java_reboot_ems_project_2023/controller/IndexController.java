package ru.sberbank.jd.java_reboot_ems_project_2023.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

        @GetMapping("/")
        public String getCurrentUserPage(@AuthenticationPrincipal UserDetails currentUser, Model model) {
            model.addAttribute("currentUser", currentUser);
            return "index";
        }
}
