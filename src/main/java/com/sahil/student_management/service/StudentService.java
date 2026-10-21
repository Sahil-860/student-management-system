package com.sahil.student_management.service;

import com.sahil.student_management.exception.ResourceNotFoundException;
import com.sahil.student_management.model.Student;
import com.sahil.student_management.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student create(Student student) {
        if (repository.existsByEmail(student.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + student.getEmail());
        }
        return repository.save(student);
    }

    public Page<Student> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Student getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    public Page<Student> getByCourse(String course, Pageable pageable) {
        return repository.findByCourseIgnoreCase(course, pageable);
    }

    public Student update(Long id, Student updated) {
        Student existing = getById(id);

        if (!existing.getEmail().equalsIgnoreCase(updated.getEmail())
                && repository.existsByEmail(updated.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + updated.getEmail());
        }

        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());
        existing.setEmail(updated.getEmail());
        existing.setCourse(updated.getCourse());
        existing.setAge(updated.getAge());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.delete(getById(id));
    }
}