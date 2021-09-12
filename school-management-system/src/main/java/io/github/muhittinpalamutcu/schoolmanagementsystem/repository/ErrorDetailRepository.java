package io.github.muhittinpalamutcu.schoolmanagementsystem.repository;

import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.ErrorDetail;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ErrorDetailRepository extends CrudRepository<ErrorDetail, Integer> {

    List<ErrorDetail> findByException(String exceptionName);
    List<ErrorDetail> findByLocalDate(LocalDate localDate);
}
