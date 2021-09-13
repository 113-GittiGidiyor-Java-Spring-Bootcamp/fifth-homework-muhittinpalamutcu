package io.github.muhittinpalamutcu.schoolmanagementsystem.repository;

import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.InstructorSalaryTransactionLogger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorSalaryTransactionRepository extends CrudRepository<InstructorSalaryTransactionLogger, Integer> {

}
