package ru.sberbank.jd.java_reboot_ems_project_2023.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.User;

public interface UserRepository extends JpaRepository<User, Long>  {
    User findByUsername(String username);
}

