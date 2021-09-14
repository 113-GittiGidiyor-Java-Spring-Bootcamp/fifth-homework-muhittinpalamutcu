package io.github.muhittinpalamutcu.schoolmanagementsystem.service;

import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.CourseDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.InstructorDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.StudentDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Course;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Instructor;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Student;
import io.github.muhittinpalamutcu.schoolmanagementsystem.exceptions.CourseIsAlreadyExistException;
import io.github.muhittinpalamutcu.schoolmanagementsystem.mappers.CourseMapper;
import io.github.muhittinpalamutcu.schoolmanagementsystem.repository.CourseRepository;
import io.github.muhittinpalamutcu.schoolmanagementsystem.repository.InstructorRepository;
import io.github.muhittinpalamutcu.schoolmanagementsystem.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    @Mock
    CourseRepository mockCourseRepository;

    @Mock
    StudentRepository mockStudentRepository;

    @Mock
    InstructorRepository mockInstructorRepository;

    @Mock
    CourseMapper mockCourseMapper;

    @InjectMocks
    CourseServiceImpl courseService;

    @Test
    void saveCourseShouldGetCourseIsAlreadyExistException() {
        // given
        final String courseCode = "SE115";
        when(mockCourseRepository.findByCourseCode(courseCode)).thenReturn(new Course());

        // when
        CourseDTO dto = new CourseDTO();
        dto.setCourseCode(courseCode);


        Executable executable = () -> courseService.save(dto);
        // then
        assertThrows(CourseIsAlreadyExistException.class, executable);
    }

    @Test
    void saveCourseShouldGetCourseIsAlreadyExistException2() {
        // given
        final String courseCode = "SE115";
        final int id = 1;
        when(mockCourseRepository.findByCourseCode(courseCode)).thenReturn(null);
        when(mockCourseRepository.existsById(id)).thenReturn(true);

        // when
        CourseDTO dto = new CourseDTO();
        dto.setCourseCode(courseCode);
        dto.setId(id);

        Executable executable = () -> courseService.save(dto);
        // then
        assertThrows(CourseIsAlreadyExistException.class, executable);
    }

    @Test
    void saveCourseOk() {
        // given
        Course course = new Course(1, "Programming 1", "SE115", 5, new ArrayList<Student>(), new Instructor(), Instant.now(), Instant.now());
        CourseDTO dto = new CourseDTO(1, "Programming 1", "SE115", 5, new ArrayList<StudentDTO>(), new InstructorDTO());

        // when
        when(mockCourseRepository.findByCourseCode(course.getCourseCode())).thenReturn(null);
        when(mockCourseRepository.existsById(course.getId())).thenReturn(false);
        when(mockCourseMapper.mapFromCourseDTOtoCourse(dto)).thenReturn(course);
        when(mockCourseRepository.save(course)).thenReturn(course);

        Course actual = courseService.save(dto).get();

        // then
        assertNotNull(actual);
        assertEquals(course, actual);
    }

}
