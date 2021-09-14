package io.github.muhittinpalamutcu.schoolmanagementsystem.controller;

import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.CourseDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Course;
import io.github.muhittinpalamutcu.schoolmanagementsystem.service.CourseServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseControllerTest {

    @Mock
    CourseServiceImpl mockCourseService;

    @InjectMocks
    CourseController courseController;

    @Test
    void saveCourse() {
        // given
        Course course = new Course();
        Optional<Course> expected = Optional.of(course);
        when(mockCourseService.save(any())).thenReturn(expected);

        // when
        CourseDTO dto = new CourseDTO();
        ResponseEntity<Course> actual = courseController.saveCourse(dto);

        // then
        assertAll(
                () -> assertNotNull(actual.getBody()),
                () -> assertEquals(expected.get(), actual.getBody())
        );
    }

    @Test
    void saveCourse2() {
        // given
        when(mockCourseService.save(any())).thenReturn(Optional.empty());

        // when
        CourseDTO dto = new CourseDTO();
        ResponseEntity<Course> actual = courseController.saveCourse(dto);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
        assertNull(actual.getBody());
    }

    @Test
    void updateCourse() {
        // given
        Course expected = new Course();
        when(mockCourseService.update(any())).thenReturn(expected);

        // when
        CourseDTO dto = new CourseDTO();
        Course actual = courseController.updateCourse(dto);

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }
}
