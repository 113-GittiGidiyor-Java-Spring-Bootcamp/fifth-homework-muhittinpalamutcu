package io.github.muhittinpalamutcu.schoolmanagementsystem.repository;

import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Instructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends CrudRepository<Instructor, Integer> {
    Instructor findByName(String name);

    Instructor findByPhoneNumber(String phoneNumber);

    void deleteInstructorByName(String name);

    @Query("select p From PermanentInstructor p order by p.fixedSalary desc")
    List<Instructor> find3HighestPaidPermanentInstructor(PageRequest pageRequest);

    @Query("select p From PermanentInstructor p order by p.fixedSalary asc ")
    List<Instructor> find3LeastPaidPermanentInstructor(PageRequest pageRequest);
}
