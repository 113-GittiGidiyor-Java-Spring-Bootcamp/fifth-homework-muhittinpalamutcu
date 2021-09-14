package io.github.muhittinpalamutcu.schoolmanagementsystem.service;

import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.StudentDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Student;
import io.github.muhittinpalamutcu.schoolmanagementsystem.exceptions.BadRequestException;
import io.github.muhittinpalamutcu.schoolmanagementsystem.mappers.StudentMapper;
import io.github.muhittinpalamutcu.schoolmanagementsystem.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    StudentRepository mockStudentRepository;

    @Mock
    StudentMapper mockStudentMapper;

    @InjectMocks
    StudentServiceImpl studentService;


    @Test
    void saveStudent() {
        // given
        Student student = new Student();
        student.setBirthDate(LocalDate.of(1996, 2, 15));
        when(mockStudentMapper.mapFromStudentDTOtoStudent(any())).thenReturn(student);
        when(mockStudentRepository.save(any())).thenReturn(student);

        // when
        StudentDTO dto = new StudentDTO();
        dto.setBirthDate(LocalDate.of(1996, 2, 15));
        Student actual = studentService.save(dto).get();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(student, actual)
        );
    }

    @Test
    void saveStudent2() {
        // given
        when(mockStudentRepository.existsById(anyInt())).thenReturn(true);

        // when
        StudentDTO dto = new StudentDTO();
        Executable executable = () -> studentService.save(dto).get();

        // then
        assertThrows(BadRequestException.class, executable);
    }
}
