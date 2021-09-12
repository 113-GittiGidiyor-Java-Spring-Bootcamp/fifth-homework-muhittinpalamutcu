package io.github.muhittinpalamutcu.schoolmanagementsystem.controller;

import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.PermanentInstructorDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.VisitingResearcherDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Instructor;
import io.github.muhittinpalamutcu.schoolmanagementsystem.service.InstructorServiceImpl;
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
public class InstructorController {

    private final InstructorServiceImpl instructorService;

    // @desc Get all instructors
    // @route Get /api/instructors
    // @access Public
    @GetMapping("/instructors")
    public ResponseEntity<List<Instructor>> findAll() {
        return new ResponseEntity<>(instructorService.findAll(), HttpStatus.OK);
    }

    // @desc Get instructor by id
    // @route Get /api/instructors/{id}
    // @access Public
    @GetMapping("/instructors/{id}")
    public Instructor findById(@PathVariable int id) {
        return instructorService.findById(id);
    }

    // @desc Save a permanent instructor
    // @route Post /api/instructors/save-permanent-instructor
    // @access Public
    @PostMapping("/instructors/save-permanent-instructor")
    public ResponseEntity<Instructor> savePermanentInstructor(@RequestBody @Valid PermanentInstructorDTO permanentInstructorDTO) {
        Optional<Instructor> instructorOptional = instructorService.save(permanentInstructorDTO);
        if (instructorOptional.isPresent()) {
            return new ResponseEntity<>(instructorOptional.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // @desc Save a visiting researcher
    // @route Post /api/instructors/save-visiting-researcher
    // @access Public
    @PostMapping("/instructors/save-visiting-researcher")
    public ResponseEntity<Instructor> saveVisitingResearcher(@RequestBody @Valid VisitingResearcherDTO visitingResearcherDTO) {
        Optional<Instructor> instructorOptional = instructorService.save(visitingResearcherDTO);
        if (instructorOptional.isPresent()) {
            return new ResponseEntity<>(instructorOptional.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    // @desc Update permanent instructor
    // @route Put /api/instructors/save-permanent-instructor
    // @access Public
    @PutMapping("/instructors/update-permanent-instructor")
    public Instructor updatePermanentInstructor(@RequestBody @Valid PermanentInstructorDTO permanentInstructorDTO) {
        return instructorService.update(permanentInstructorDTO);
    }

    // @desc Update visiting researcher
    // @route Put /api/instructors/save-permanent-instructor
    // @access Public
    @PutMapping("/instructors/update-visiting-researcher")
    public Instructor updateVisitingResearcher(@RequestBody @Valid VisitingResearcherDTO visitingResearcherDTO) {
        return instructorService.update(visitingResearcherDTO);
    }

    // @desc Delete instructor by id
    // @route Delete /api/instructors/{id}
    // @access Public
    @DeleteMapping("/instructors/{id}")
    public String deleteInstructorById(@PathVariable int id) {
        instructorService.deleteById(id);
        return "Instructor deleted...";
    }

    // @desc Get instructor by name
    // @route Get /api/instructors/getByName/{name}
    // @access Public
    @GetMapping("/instructors/getByName/{name}")
    public Instructor findByName(@PathVariable String name) {
        return instructorService.findByName(name);
    }

    // @desc Delete instructor by name
    // @route Get /api/instructors/deleteByName/{name}
    // @access Public
    @DeleteMapping("/instructors/deleteByName/{name}")
    public String deleteInstructorByName(@PathVariable String name) {
        instructorService.deleteByName(name);
        return "Instructor deleted...";
    }
}
