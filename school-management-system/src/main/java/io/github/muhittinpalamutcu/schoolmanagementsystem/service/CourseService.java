package io.github.muhittinpalamutcu.schoolmanagementsystem.service;

import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.CourseDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Course;

import java.util.Optional;

public interface CourseService extends BaseService<Course> {
    Optional<Course> save(CourseDTO courseDTO);
    Course update(CourseDTO courseDTO);
}
