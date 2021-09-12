package io.github.muhittinpalamutcu.schoolmanagementsystem.service;

import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.ErrorDetail;
import io.github.muhittinpalamutcu.schoolmanagementsystem.repository.ErrorDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ErrorDetailService {

    @Autowired
    private ErrorDetailRepository errorDetailRepository;

    /**
     * This method return all the errors exist in the database.
     *
     * @param "args Unused."
     * @return List<ErrorDetail>
     */
    public List<ErrorDetail> findAll() {
        return (List<ErrorDetail>) errorDetailRepository.findAll();
    }

    /**
     * This method save errorDetail to database.
     *
     * @param errorDetail
     * @return ErrorDetail
     */
    @Transactional
    public ErrorDetail save(ErrorDetail errorDetail) {
        return errorDetailRepository.save(errorDetail);
    }

    /**
     * This method return errorDetail list that are filtered by exception name.
     *
     * @param exception
     * @return List<ErrorDetail>
     */
    public List<ErrorDetail> findByException(String exception) {
        return errorDetailRepository.findByException(exception);
    }

    /**
     * This method return errorDetail list that are filtered by date.
     *
     * @param localDate
     * @return List<ErrorDetail>
     */
    public List<ErrorDetail> findByLocalDate(LocalDate localDate) {
        return errorDetailRepository.findByLocalDate(localDate);
    }
}
