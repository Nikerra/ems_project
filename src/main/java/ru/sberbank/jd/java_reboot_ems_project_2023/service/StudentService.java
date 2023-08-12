package ru.sberbank.jd.java_reboot_ems_project_2023.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.Student;
import ru.sberbank.jd.java_reboot_ems_project_2023.repository.StudentRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public void save(Student student) {
        studentRepository.save(student);
    }

    public void studentUpdate(Long id) {
      Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid ID: " + id));
      studentRepository.save(student);
    }

    public void studentDeleteById(Long id) {
//        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid ID: " + id));
        studentRepository.deleteById(id);
    }

    public List<Student> studentFindAll() {
        return studentRepository.findAll();
    }
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public void removeStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public List<Student> getAllStudents() {
        return  studentRepository.findAll();
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public void updateStudent(Student student) {
        studentRepository.save(student);
    }

    public Student getStudentById(Long id) {

        return studentRepository.findStudentById(id);
    }

    public void createStudent(Student student) {
        studentRepository.save(student);
    }
}
