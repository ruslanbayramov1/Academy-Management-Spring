package org.springframework.academymanagement.repository;

import org.springframework.academymanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    Optional<Student> findByEmail(String email);

    Optional<Student> findByStudentCode(String studentCode);
}
