package io.github.muhittinpalamutcu.schoolmanagementsystem.mappers;

import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.CourseDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course mapFromCourseDTOtoCourse(CourseDTO courseDTO);
    CourseDTO mapFromCourseToCourseDTO(Course course);
}
