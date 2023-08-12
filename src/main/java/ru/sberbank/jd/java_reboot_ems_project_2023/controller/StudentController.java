package ru.sberbank.jd.java_reboot_ems_project_2023.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.Group;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.Role;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.Student;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.User;
import ru.sberbank.jd.java_reboot_ems_project_2023.repository.GroupRepository;
import ru.sberbank.jd.java_reboot_ems_project_2023.repository.UserRepository;
import ru.sberbank.jd.java_reboot_ems_project_2023.service.StudentService;
import ru.sberbank.jd.java_reboot_ems_project_2023.service.UserService;

import java.util.List;

@Controller
@RequestMapping(value = "/student")
@RequiredArgsConstructor
public class StudentController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    @GetMapping()
    public String Student(@AuthenticationPrincipal UserDetails currentUser,Model model) {

        Group group = groupRepository.getGroupById(0L);
        System.out.println("group: " + group.getTasks());
        User user = userRepository.findByUsername(currentUser.getUsername());
        model.addAttribute("currentUser", user);
        model.addAttribute("groupTask", group);
//        model.addAttribute("userGroup", user);

//        model.addAttribute("userGroupTask", groupRepository..findByUsername(currentUser.getUsername()));
        System.out.println("User: " + currentUser);
//
//        String user = currentUser.getUsername();
//        System.out.println(userRepository.findByUsername(user));
        return "student";
    }

//    @PostMapping("/save")
//    public ResponseEntity<Student> newStudent(@RequestBody Student student){
//        studentService.save(student);
//        return null;
//    }

    @PostMapping("/update")
    public String updateUser( @RequestParam("id") Long userId, @ModelAttribute("id_r") Long roleId) {
        User user =userService.findUserById(userId);
        Role role = userService.findRoleById(roleId);
        user.setRoles(role);
        userService.save(user);
        return "redirect:/student";
    }

//    @PostMapping("/update/{id}")
//    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id) {
//        studentService.studentUpdate(id);
//        return null;
//    }
//
//    @GetMapping("/delete/{id}")
//    public RedirectView deleteBook(@PathVariable("id") Long id) {
//        studentService.studentDeleteById(id);
//        return null;
//    }
}
