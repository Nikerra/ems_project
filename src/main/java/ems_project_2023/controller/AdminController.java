package ems_project_2023.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ems_project_2023.dao.entity.Group;
import ems_project_2023.dao.entity.Role;
import ems_project_2023.dao.entity.User;
import ems_project_2023.dao.entity.UserAdminResp;
import ems_project_2023.GroupService;
import ems_project_2023.UserAdminRespService;
import ems_project_2023.UserService;


import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final GroupService groupService;
    private final UserAdminRespService userAdminRespService;

    /***
     *
     * @param currentUser authorized user
     * @param model model
     * @return list users
     */
    @GetMapping()
    public String userList(@AuthenticationPrincipal UserDetails currentUser,Model model) {
        model.addAttribute("userForm", new User());
        model.addAttribute("allUsers", userService.allUsers());
        model.addAttribute("allGroups", groupService.getAllGroups());
        model.addAttribute("allRoles",userService.allRoles());
        model.addAttribute("allResp",userAdminRespService.allResp());
        model.addAttribute("group", new Group());
        model.addAttribute("role", new Role());
        model.addAttribute("currentUser", currentUser);
        return "admin";
    }
    @GetMapping("/createUser")
    public String createUser(@AuthenticationPrincipal UserDetails currentUser,Model model) {
        model.addAttribute("userForm", new User());
        model.addAttribute("allUsers", userService.allUsers());
        model.addAttribute("allGroups", groupService.getAllGroups());
        model.addAttribute("allRoles",userService.allRoles());
        model.addAttribute("allResp",userAdminRespService.allResp());
        model.addAttribute("group", new Group());
        model.addAttribute("role", new Role());
        model.addAttribute("currentUser", currentUser);
        return "adminCreateUser";
    }
    /***
     *
     * @param userId id user
     * @return model list users
     */
    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") Long userId) {
        userService.deleteUser(userId);
        return "redirect:/admin";
    }

    /***
     *
     * @param userId id user
     * @param roleId id role
     * @param groupId id group
     * @return model list users
     */
    @PostMapping("/update")
    public String updateUser( @RequestParam("id") Long userId,
                            @ModelAttribute("roleId") Long roleId,
                            @ModelAttribute("groupId") Long groupId) {
        User user =userService.findUserById(userId);
        Role role = userService.findRoleById(roleId);
        Group group = groupService.getGroupById(groupId);
        user.setRole(role);
        user.setGroupUser(group);
        userService.save(user);
        return "redirect:/admin";
    }

    /***
     *
     * @param userForm form for user
     * @param bindingResult checking the userForm
     * @param model model
     * @return model list users
     */
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
    @PostMapping("/tasksToCheck")
    public String handlePostRequest(@ModelAttribute("res_id") Boolean taskResp , @RequestParam Long id) {

        UserAdminResp userAdminResp =  userAdminRespService.findByRespId(id);
        userAdminResp.setResult(taskResp);
        userAdminRespService.save(userAdminResp);
        return "redirect:/admin";
    }
}
