package ems_project_2023.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    /***
     *
     * @param currentUser  authorized user
     * @param model model
     * @return main page
     */
    @GetMapping("/")
    public String getCurrentUserPage(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        model.addAttribute("currentUser", currentUser);
        return "index";
    }
}
