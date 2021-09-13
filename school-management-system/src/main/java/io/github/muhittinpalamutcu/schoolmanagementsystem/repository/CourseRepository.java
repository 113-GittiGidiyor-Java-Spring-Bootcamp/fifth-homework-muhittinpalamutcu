package io.github.muhittinpalamutcu.schoolmanagementsystem.repository;

import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {
    Course findByName(String name);

    void deleteCourseByName(String name);

    Course findByCourseCode(String courseCode);

    @Query("SELECT " +
            "  CASE " +
            "   WHEN " +
            "       COUNT(c)>0 " +
            "   THEN " +
            "       TRUE " +
            "   ELSE " +
            "       FALSE " +
            "   END " +
            "FROM Course c " +
            "WHERE c.id = ?1")
    boolean selectExistsSsid(int id);
}
