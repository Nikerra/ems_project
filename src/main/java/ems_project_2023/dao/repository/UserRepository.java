package ems_project_2023.dao.repository;

import ems_project_2023.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>  {
    User findByName(String username);
    List<User> findAllByGroupUserId(Long id);
    List<User> getUserByRoleId(Long id);

}

