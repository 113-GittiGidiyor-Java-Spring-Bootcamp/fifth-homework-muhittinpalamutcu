package io.github.muhittinpalamutcu.schoolmanagementsystem.repository;

import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {
    Course findByName(String name);
    void deleteCourseByName(String name);
    Course findByCourseCode(String courseCode);
}
