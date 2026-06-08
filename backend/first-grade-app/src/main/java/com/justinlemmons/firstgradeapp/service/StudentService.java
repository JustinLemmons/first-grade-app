package com.justinlemmons.firstgradeapp.service;

import com.justinlemmons.firstgradeapp.entity.Student;
import com.justinlemmons.firstgradeapp.entity.StudentAssignment;
import com.justinlemmons.firstgradeapp.entity.User;
import com.justinlemmons.firstgradeapp.repository.StudentAssignmentRepository;
import com.justinlemmons.firstgradeapp.repository.StudentRepository;
import com.justinlemmons.firstgradeapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final StudentAssignmentRepository studentAssignmentRepository;

    public Student getAuthenticatedStudent(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return studentRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public List<StudentAssignment> getAssignmentsForAuthenticatedStudent(Long userId){
        Student student = getAuthenticatedStudent(userId);
        return studentAssignmentRepository.findByStudent(student);
    }

    public StudentAssignment getStudentAssignmentById(Long userId, Long studentAssignmentId){
        Student student = getAuthenticatedStudent(userId);
        StudentAssignment studentAssignment = studentAssignmentRepository.findById(studentAssignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
        if(!studentAssignment.getStudent().getId().equals(student.getId())){
            throw new RuntimeException("Unauthorized access");
        }
        return studentAssignment;
    }
}
