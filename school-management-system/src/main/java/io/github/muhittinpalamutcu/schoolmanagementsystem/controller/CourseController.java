package io.github.muhittinpalamutcu.schoolmanagementsystem.controller;

import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.CourseDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Course;
import io.github.muhittinpalamutcu.schoolmanagementsystem.service.CourseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CourseController {

    private final CourseServiceImpl courseService;

    // @desc Get all courses
    // @route Get /api/courses
    // @access Public
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> findAll() {
        return new ResponseEntity<>(courseService.findAll(), HttpStatus.OK);
    }

    // @desc Get course by id
    // @route Get /api/courses/{id}
    // @access Public
    @GetMapping("/courses/{id}")
    public Course findById(@PathVariable int id) {
        return courseService.findById(id);
    }

    // @desc Save a course
    // @route Post /api/courses
    // @access Public
    @PostMapping("/courses")
    public ResponseEntity<Course> saveCourse(@RequestBody @Valid CourseDTO courseDTO) {
        Optional<Course> courseOptional = courseService.save(courseDTO);
        if (courseOptional.isPresent()) {
            return new ResponseEntity<>(courseOptional.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // @desc Update a course
    // @route Put /api/courses
    // @access Public
    @PutMapping("/courses")
    public Course updateCourse(@RequestBody CourseDTO courseDTO) {
        return courseService.update(courseDTO);
    }

    // @desc Delete course by id
    // @route Delete /api/courses/{id}
    // @access Public
    @DeleteMapping("/courses/{id}")
    public String deleteCourseById(@PathVariable int id) {
        courseService.deleteById(id);
        return "Course deleted...";
    }

    // @desc Get course by name
    // @route Get /api/course/getByName/{name}
    // @access Public
    @GetMapping("/courses/getByName/{name}")
    public Course findByName(@PathVariable String name) {
        return courseService.findByName(name);
    }

    // @desc Delete course by name
    // @route Get /api/courses/deleteByName/{name}
    // @access Public
    @DeleteMapping("/courses/deleteByName/{name}")
    public String deleteCourseByName(@PathVariable String name) {
        courseService.deleteByName(name);
        return "Course deleted...";
    }

    // @desc Register student to a course
    // @route Get /api/courses/register-student/{course-code}
    //@access Public
    @PatchMapping("/courses/register-student/{courseCode}")
    public ResponseEntity<Course> registerStudentToCourse(@PathVariable String courseCode, @RequestBody int studentId) {
        Optional<Course> course = courseService.registerStudentToCourse(courseCode, studentId);
        return course.isPresent() ? new ResponseEntity<>(course.get(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // @desc Register instructor to a course
    // @route Get /api/courses/register-student/{course-code}
    //@access Public
    @PatchMapping("/courses/register-instructor/{courseCode}")
    public ResponseEntity<Course> registerInstructorToCourse(@PathVariable String courseCode, @RequestBody int instructorId) {
        Optional<Course> course = courseService.registerInstructorToCourse(courseCode, instructorId);
        return course.isPresent() ? new ResponseEntity<>(course.get(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
