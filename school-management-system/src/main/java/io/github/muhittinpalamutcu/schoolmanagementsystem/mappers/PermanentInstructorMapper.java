package io.github.muhittinpalamutcu.schoolmanagementsystem.mappers;

import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.PermanentInstructorDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.PermanentInstructor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermanentInstructorMapper {
    PermanentInstructor mapFromPermanentInstructorDTOtoPermanentInstructor(PermanentInstructorDTO permanentInstructorDTO);

    PermanentInstructorDTO mapFromPermanentInstructorToPermanentInstructorDTO(PermanentInstructor permanentInstructor);
}
