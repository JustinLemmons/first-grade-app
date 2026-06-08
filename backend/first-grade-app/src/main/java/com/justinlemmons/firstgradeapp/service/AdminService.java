package com.justinlemmons.firstgradeapp.service;

import com.justinlemmons.firstgradeapp.entity.Student;
import com.justinlemmons.firstgradeapp.entity.StudentAssignment;
import com.justinlemmons.firstgradeapp.entity.Teacher;
import com.justinlemmons.firstgradeapp.repository.StudentAssignmentRepository;
import com.justinlemmons.firstgradeapp.repository.StudentRepository;
import com.justinlemmons.firstgradeapp.repository.TeacherRepository;
import com.justinlemmons.firstgradeapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final StudentAssignmentRepository studentAssignmentRepository;

    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public List<StudentAssignment> findAllStudentAssignments() {
        return studentAssignmentRepository.findAll();
    }

    public Student addStudentToTeacher(Long studentId, Long teacherId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        student.setTeacher(teacher);
        return studentRepository.save(student);
    }

    public void removeStudent(Long studentId) {
        studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        studentRepository.deleteById(studentId);
    }
}
