package io.github.muhittinpalamutcu.schoolmanagementsystem.service;

import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.InstructorDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Instructor;

import java.util.Optional;

public interface InstructorService extends BaseService<Instructor> {
    Optional<Instructor> save(InstructorDTO instructorDTO);
    Instructor update(InstructorDTO instructorDTO);
}
