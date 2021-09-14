package io.github.muhittinpalamutcu.schoolmanagementsystem.service;

import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.PermanentInstructorDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.VisitingResearcherDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Instructor;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.PermanentInstructor;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.VisitingResearcher;
import io.github.muhittinpalamutcu.schoolmanagementsystem.exceptions.BadRequestException;
import io.github.muhittinpalamutcu.schoolmanagementsystem.mappers.PermanentInstructorMapper;
import io.github.muhittinpalamutcu.schoolmanagementsystem.mappers.VisitingResearcherMapper;
import io.github.muhittinpalamutcu.schoolmanagementsystem.repository.InstructorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InstructorServiceTest {
    @Mock
    InstructorRepository mockInstructorRepository;

    @Mock
    PermanentInstructorMapper mockPermanentInstructorMapper;

    @Mock
    VisitingResearcherMapper mockVisitingResearcherMapper;

    @InjectMocks
    InstructorServiceImpl instructorService;


    @Test
    void savePermanentInstructor() {
        // given
        Instructor instructor = new PermanentInstructor();
        when(mockPermanentInstructorMapper.mapFromPermanentInstructorDTOtoPermanentInstructor(any())).thenReturn((PermanentInstructor) instructor);
        when(mockInstructorRepository.save(any())).thenReturn(instructor);

        // when
        PermanentInstructorDTO dto = new PermanentInstructorDTO();
        Instructor actual = instructorService.save(dto).get();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(instructor, actual)
        );
    }

    @Test
    void saveVisitingResearcher() {
        // given
        Instructor instructor = new VisitingResearcher();
        when(mockVisitingResearcherMapper.mapFromVisitingResearcherDTOtoVisitingResearcher(any())).thenReturn((VisitingResearcher) instructor);
        when(mockInstructorRepository.save(any())).thenReturn(instructor);

        // when
        VisitingResearcherDTO dto = new VisitingResearcherDTO();
        Instructor actual = instructorService.save(dto).get();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(instructor, actual)
        );
    }

    @Test
    void savePermanentInstructor2() {
        // given
        when(mockInstructorRepository.existsById(anyInt())).thenReturn(true);

        // when
        PermanentInstructorDTO dto = new PermanentInstructorDTO();
        Executable executable = () -> instructorService.save(dto).get();

        // then
        assertThrows(BadRequestException.class, executable);
    }

    @Test
    void saveVisitingResearcher2() {
        // given
        when(mockInstructorRepository.existsById(anyInt())).thenReturn(true);

        // when
        VisitingResearcherDTO dto = new VisitingResearcherDTO();
        Executable executable = () -> instructorService.save(dto).get();

        // then
        assertThrows(BadRequestException.class, executable);
    }
}
