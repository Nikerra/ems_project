package ru.sberbank.jd.java_reboot_ems_project_2023.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.Group;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.Task;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.User;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.repository.GroupRepository;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.repository.TaskRepository;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public Group save(Group group) {
        return groupRepository.save(group);
    }

    public Group getGroupById(Long groupId) {
        return groupRepository.getGroupById(groupId);
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    /***
     *
     * @return List Group
     */
    public List<Group> getGroupsIdNot1() {
        List<Group> groupAll=groupRepository.findAll();
        return groupAll.stream()
                .filter(group -> group.getId() != 1)
                .collect(Collectors.toList());
    }

    /***
     * Delete group
     * @param id Id Group
     */
    public void deleteGroup(Long id) {

        List<Task> task = taskRepository.findAllByGroupTaskId(id);
        if (task != null) {
            for (Task taskGroup : task) {
                taskGroup.setGroupTask(groupRepository.getGroupById(1L));
            }
            taskRepository.saveAll(task);
        }
        List<User> user = userRepository.findAllByGroupUserId(id);
        if (user != null) {
            for (User userGroup : user) {
                userGroup.setGroupUser(groupRepository.getGroupById(1L));
            }
            userRepository.saveAll(user);
        }
        groupRepository.deleteById(id);
    }

    /***
     * Update group
     * @param group Group
     */
    public void updateGroup(Group group) {
        groupRepository.save(group);
    }

    /***
     *  Get List<Group>
     * @param user Entity User
     * @return List group
     */
    public List<Group> getAllGroupsTeacher(User user) {
        return groupRepository.findAllByUsers(user);
    }
}
