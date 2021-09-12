package io.github.muhittinpalamutcu.schoolmanagementsystem.service;

import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.StudentDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Student;

import java.util.Optional;

public interface StudentService extends BaseService<Student> {

    Optional<Student> save(StudentDTO studentDTO);

    Student update(StudentDTO studentDTO);
}
