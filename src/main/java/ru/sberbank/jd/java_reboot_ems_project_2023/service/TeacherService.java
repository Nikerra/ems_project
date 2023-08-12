package ru.sberbank.jd.java_reboot_ems_project_2023.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.Teacher;
import ru.sberbank.jd.java_reboot_ems_project_2023.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public Teacher findByEmail(String email) {
        return teacherRepository.findByEmailIgnoreCase(email);
    }
    public List<Teacher> getAllTeacher() {
        return  teacherRepository.findAll();
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    public void updateTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public Optional<Teacher> getTeacherById(Long id) {

        return teacherRepository.findTeacherById(id);
    }

    public void createTeacher(Teacher teacher) {
        teacher.setRole();
        System.out.println("method createTeacher="+teacher);
        teacherRepository.save(teacher);
    }
}
