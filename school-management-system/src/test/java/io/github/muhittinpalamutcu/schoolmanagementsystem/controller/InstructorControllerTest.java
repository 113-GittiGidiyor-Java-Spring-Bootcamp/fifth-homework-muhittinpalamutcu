package io.github.muhittinpalamutcu.schoolmanagementsystem.controller;

import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.PermanentInstructorDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.VisitingResearcherDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Instructor;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.PermanentInstructor;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.VisitingResearcher;
import io.github.muhittinpalamutcu.schoolmanagementsystem.service.InstructorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InstructorControllerTest {

    @Mock
    InstructorServiceImpl mockInstructorService;

    @InjectMocks
    InstructorController instructorController;

    @Test
    void savePermanentInstructor() {
        // given
        Instructor instructor = new PermanentInstructor();
        Optional<Instructor> expected = Optional.of(instructor);
        when(mockInstructorService.save(any())).thenReturn(expected);

        // when
        PermanentInstructorDTO dto = new PermanentInstructorDTO();
        Instructor actual = this.instructorController.savePermanentInstructor(dto).getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.get(), actual)
        );
    }

    @Test
    void saveVisitingResearcher() {
        // given
        Instructor instructor = new VisitingResearcher();
        Optional<Instructor> expected = Optional.of(instructor);
        when(mockInstructorService.save(any())).thenReturn(expected);

        // when
        VisitingResearcherDTO dto = new VisitingResearcherDTO();
        Instructor actual = this.instructorController.saveVisitingResearcher(dto).getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.get(), actual)
        );
    }

    @Test
    void savePermanentInstructor2() {
        // given
        when(mockInstructorService.save(any())).thenReturn(Optional.empty());

        // when
        PermanentInstructorDTO dto = new PermanentInstructorDTO();
        ResponseEntity<Instructor> actual = this.instructorController.savePermanentInstructor(dto);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
    }

    @Test
    void saveVisitingResearcher2() {
        // given
        when(mockInstructorService.save(any())).thenReturn(Optional.empty());

        // when
        VisitingResearcherDTO dto = new VisitingResearcherDTO();
        ResponseEntity<Instructor> actual = this.instructorController.saveVisitingResearcher(dto);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
    }

    @Test
    void updatePermanentInstructor() {
        // given
        Instructor instructor = new PermanentInstructor();
        Instructor expected = instructor;
        when(mockInstructorService.update(any())).thenReturn(expected);

        // when
        PermanentInstructorDTO dto = new PermanentInstructorDTO();
        Instructor actual = this.instructorController.updatePermanentInstructor(dto);

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void updateVisitingResearcher() {
        // given
        Instructor instructor = new VisitingResearcher();
        Instructor expected = instructor;
        when(mockInstructorService.update(any())).thenReturn(expected);

        // when
        VisitingResearcherDTO dto = new VisitingResearcherDTO();
        Instructor actual = this.instructorController.updateVisitingResearcher(dto);

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }


}
