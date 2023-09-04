package ems_project_2023.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ems_project_2023.dao.entity.Group;
import ems_project_2023.dao.entity.User;
import ems_project_2023.dao.entity.UserAdminResp;
import ems_project_2023.service.GroupService;
import ems_project_2023.service.UserAdminRespService;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final GroupService groupService;
    private final UserAdminRespService userAdminRespService;
    /***
     *
     * @param currentUser authorized user
     * @param model model
     * @return form for user
     */
    @GetMapping()
    public String getCurrentUserPage(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        User user = (User) currentUser;
        List<UserAdminResp> allResp = userAdminRespService.getUserAdminRespByUser(user.getId());
        List<Group> allGroup = groupService.getGroupsIdNot1();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("allResp", allResp);
        model.addAttribute("allGroup", allGroup);
        return "user";
    }
    @PostMapping
    public String addUserAdminResp(@AuthenticationPrincipal UserDetails currentUser,
                                   @RequestParam Long group,
                                   @RequestParam String text) {

        User user = (User) currentUser;
        UserAdminResp userAdminResp = new UserAdminResp();
        userAdminResp.setUser(user);
        userAdminResp.setGroup(groupService.getGroupById(group));
        userAdminResp.setText(text);
        userAdminRespService.save(userAdminResp);
        return "redirect:/user";
    }

}