package ems_project_2023.controller;

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
import ems_project_2023.dao.entity.Group;
import ems_project_2023.dao.entity.User;
import ems_project_2023.service.GroupService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    /**
     *
     * @param currentUser type springframework.security.core.userdetails.UserDetails
     * @param model type springframework.ui.Model
     * @return html page
     */
    @GetMapping("/groupPanel")
    public String groupPanel(@AuthenticationPrincipal UserDetails currentUser,Model model) {
        User user = (User) currentUser;
        List<Group> groups = null;
        if (user.getRole().getName().equals("ROLE_TEACHER")) {
            groups = groupService.getAllGroupsTeacher(user);
        }
        List<Group> allGroup = groupService.getAllGroups();
        model.addAttribute("currentUser" + currentUser);
        model.addAttribute("allGroups", allGroup);
        model.addAttribute("allGroupForTeacher", groups);
        return "groupPanel";
    }

    /**
     *
     * @param group type dao.entity.Group
     * @param bindingResult type springframework.validation.BindingResult
     * @return html page
     */
    @PostMapping("/groupPanel")
    public String createGroup(@Valid @ModelAttribute("group")Group group, BindingResult bindingResult) {
        groupService.save(group);
        return "redirect:/teacher/groupPanel?";
    }

    /**
     *
     * @param group dao.entity.Group
     * @param bindingResult type springframework.validation.BindingResult
     * @param model type springframework.ui.Model
     * @return html page
     */
    @PostMapping("/groupPanel/delete")
    public String deleteGroup(@Valid @ModelAttribute("group") Group group, BindingResult bindingResult,
                              Model model) {
        groupService.deleteGroup(group.getId());
        return "redirect:/teacher/groupPanel?";
    }
}
