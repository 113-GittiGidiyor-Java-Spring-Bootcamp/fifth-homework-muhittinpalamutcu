package io.github.muhittinpalamutcu.schoolmanagementsystem.repository;

import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
    Student findByName(String name);

    void deleteStudentByName(String name);

    @Query("select count(s) from Student s  group by s.gender")
    List<?> getGenderWithGrouping();
}
