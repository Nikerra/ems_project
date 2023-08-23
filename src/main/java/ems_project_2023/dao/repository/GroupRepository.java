package ems_project_2023.dao.repository;

import ems_project_2023.dao.entity.User;
import ems_project_2023.dao.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Group getGroupById(Long groupId);
    List<Group> findAllByUsers(User user);

}