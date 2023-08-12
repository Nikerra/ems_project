package ru.sberbank.jd.java_reboot_ems_project_2023.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.Group;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.Task;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.User;
import ru.sberbank.jd.java_reboot_ems_project_2023.repository.GroupRepository;

import java.util.List;

@Service

public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public Group save(Group group) {
        return groupRepository.save(group);
    }

    public Group getGroupById(Long groupId) {
        return groupRepository.getGroupById(groupId);
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public void deleteGroup(Long id) {
         groupRepository.deleteById(id);
    }

    public void updateGroup(Group group) {
        groupRepository.save(group);
    }

    public List<Group> getAllGroupsTeacher(User user) {
        return groupRepository.findAllByUsers(user);
    }
}
