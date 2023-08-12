package ru.sberbank.jd.java_reboot_ems_project_2023.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findStudentById(Long id);
}