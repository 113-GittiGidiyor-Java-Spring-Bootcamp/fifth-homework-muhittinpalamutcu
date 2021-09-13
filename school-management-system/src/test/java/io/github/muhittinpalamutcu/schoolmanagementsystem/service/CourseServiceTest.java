package io.github.muhittinpalamutcu.schoolmanagementsystem.service;

import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.CourseDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Course;
import io.github.muhittinpalamutcu.schoolmanagementsystem.exceptions.CourseIsAlreadyExistException;
import io.github.muhittinpalamutcu.schoolmanagementsystem.mappers.CourseMapper;
import io.github.muhittinpalamutcu.schoolmanagementsystem.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    @Mock
    CourseRepository mockCourseRepository;

    @Mock
    CourseMapper mockCourseMapper;

    @InjectMocks
    CourseServiceImpl courseService;


    @Test
    void saveCourse() {
        // given
        Course course = new Course();
        when(mockCourseMapper.mapFromCourseDTOtoCourse(any())).thenReturn(course);
        when(mockCourseRepository.save(any())).thenReturn(course);

        // when
        CourseDTO dto = new CourseDTO();
        Course actual = this.courseService.save(dto).get();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(course, actual)
        );
    }

    @Test
    void saveCourse2() {
        // given
        Course course = new Course();
        when(mockCourseRepository.existsById(anyInt())).thenReturn(Boolean.TRUE);

        // when
        CourseDTO dto = new CourseDTO();
        Executable executable = () -> this.courseService.save(dto).get();

        // then
        assertThrows(CourseIsAlreadyExistException.class, executable);
    }

}
