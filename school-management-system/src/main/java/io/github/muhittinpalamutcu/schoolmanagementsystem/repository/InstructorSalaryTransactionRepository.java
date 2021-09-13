package io.github.muhittinpalamutcu.schoolmanagementsystem.repository;

import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.InstructorSalaryTransactionLogger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InstructorSalaryTransactionRepository extends CrudRepository<InstructorSalaryTransactionLogger, Integer> {

    List<InstructorSalaryTransactionLogger> findByInstructorId(int id);
    List<InstructorSalaryTransactionLogger> findByTransactionDateTime(LocalDate localDate);
}
