package com.justinlemmons.firstgradeapp.service;

import com.justinlemmons.firstgradeapp.entity.*;
import com.justinlemmons.firstgradeapp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final AssignmentRepository assignmentRepository;
    private final StudentAssignmentRepository studentAssignmentRepository;

    public Teacher getAuthenticatedTeacher(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return teacherRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
    }

    public List<Student> getStudentsForAuthenticatedTeacher(Long userId){
        Teacher teacher = getAuthenticatedTeacher(userId);
        return studentRepository.findByTeacher(teacher);
    }

    public List<Assignment> getAssignmentsForAuthenticatedTeacher(Long userId){
        Teacher teacher = getAuthenticatedTeacher(userId);
        return assignmentRepository.findByTeacher(teacher);
    }

    public Assignment createAssignment(Long userId, Assignment assignment){
        Teacher teacher = getAuthenticatedTeacher(userId);
        assignment.setTeacher(teacher);
        return assignmentRepository.save(assignment);
    }

    public Assignment updateAssignment(Long userId, Long assignmentId, Assignment assignment){
        Teacher teacher = getAuthenticatedTeacher(userId);

        Assignment currentAssignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        if(!currentAssignment.getTeacher().getId().equals(teacher.getId())){
            throw new  RuntimeException("Unauthorized request");
        }

        currentAssignment.setTitle(assignment.getTitle());
        currentAssignment.setDescription(assignment.getDescription());
        currentAssignment.setSubject(assignment.getSubject());
        currentAssignment.setDueDate(assignment.getDueDate());
        return assignmentRepository.save(currentAssignment);
    }

    public void deleteAssignment(Long userId, Long assignmentId){
        Teacher teacher = getAuthenticatedTeacher(userId);
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        if(!teacher.getId().equals(assignment.getTeacher().getId())) {
            throw new RuntimeException("Unauthorized request");
        }
        assignmentRepository.delete(assignment);
    }

    public StudentAssignment gradeStudentAssignment(Long userId, Long studentAssignmentId, BigDecimal grade){
        Teacher teacher = getAuthenticatedTeacher(userId);
        StudentAssignment studentAssignment = studentAssignmentRepository.findById(studentAssignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
        Student student = studentAssignment.getStudent();
        if(!teacher.getId().equals(student.getTeacher().getId())) {
            throw new  RuntimeException("Unauthorized request");
        }
        studentAssignment.setGrade(grade);
        return studentAssignmentRepository.save(studentAssignment);
    }
}
