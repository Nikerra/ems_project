package ru.sberbank.jd.java_reboot_ems_project_2023.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.Teacher;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findByEmailIgnoreCase(String email);
    Optional<Teacher> findTeacherById(Long id);
}
