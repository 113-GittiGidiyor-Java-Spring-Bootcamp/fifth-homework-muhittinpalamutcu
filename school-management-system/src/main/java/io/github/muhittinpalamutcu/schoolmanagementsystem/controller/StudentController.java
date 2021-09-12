package io.github.muhittinpalamutcu.schoolmanagementsystem.controller;

import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.StudentDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Student;
import io.github.muhittinpalamutcu.schoolmanagementsystem.service.StudentServiceImpl;
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
public class StudentController {

    private final StudentServiceImpl studentService;

    // @desc Get all students
    // @route Get /api/students
    // @access Public
    @GetMapping("/students")
    public ResponseEntity<List<Student>> findAll() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    // @desc Get student by id
    // @route Get /api/students/{id}
    // @access Public
    @GetMapping("/students/{id}")
    public Student findById(@PathVariable int id) {
        return studentService.findById(id);
    }

    // @desc Save a student
    // @route Post /api/students
    // @access Public
    @PostMapping("/students")
    public ResponseEntity<Student> saveStudent(@RequestBody @Valid StudentDTO studentDTO) {
        Optional<Student> studentOptional = studentService.save(studentDTO);
        if (studentOptional.isPresent()) {
            return new ResponseEntity<>(studentOptional.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // @desc Update a student
    // @route Put /api/students
    // @access Public
    @PutMapping("/students")
    public Student updateStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.update(studentDTO);
    }

    // @desc Delete student by id
    // @route Delete /api/students/{id}
    // @access Public
    @DeleteMapping("/students/{id}")
    public String deleteStudentById(@PathVariable int id) {
        studentService.deleteById(id);
        return "Student deleted...";
    }

    // @desc Get student by name
    // @route Get /api/students/getByName/{name}
    // @access Public
    @GetMapping("/students/getByName/{name}")
    public Student findByName(@PathVariable String name) {
        return studentService.findByName(name);
    }

    // @desc Get student gender with grouping
    // @route Get /students/getGenderWithGrouping
    // @access Public
    @GetMapping("/students/getGenderWithGrouping")
    public List<?> getGenderWithGrouping() {
        return studentService.getGenderWithGrouping();
    }

    // @desc Delete student by name
    // @route Get /api/students/deleteByName/{name}
    // @access Public
    @DeleteMapping("/students/deleteByName/{name}")
    public String deleteStudentByName(@PathVariable String name) {
        studentService.deleteByName(name);
        return "Student deleted...";
    }
}
