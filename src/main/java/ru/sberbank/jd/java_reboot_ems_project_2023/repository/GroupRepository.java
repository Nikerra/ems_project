package ru.sberbank.jd.java_reboot_ems_project_2023.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.Group;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.User;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Group getGroupById(Long groupId);

    List<Group> findAllByUsers(User user);
}