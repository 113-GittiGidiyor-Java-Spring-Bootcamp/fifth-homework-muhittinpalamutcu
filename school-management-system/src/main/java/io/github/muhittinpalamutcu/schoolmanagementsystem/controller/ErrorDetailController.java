package io.github.muhittinpalamutcu.schoolmanagementsystem.controller;

import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.ErrorDetail;
import io.github.muhittinpalamutcu.schoolmanagementsystem.service.ErrorDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ErrorDetailController {

    private final ErrorDetailService errorDetailService;

    // @desc Get all error details
    // @route Get /api/error-details
    // @access Public
    @GetMapping("/error-details")
    public ResponseEntity<List<ErrorDetail>> findAll() {
        return new ResponseEntity<>(errorDetailService.findAll(), HttpStatus.OK);
    }

    // @desc Get errors by exception name
    // @route Get /api/error-details/exception/{name}
    // @access Public
    @GetMapping("/error-details/exception-by-name/{name}")
    public List<ErrorDetail> findByExceptionName(@PathVariable String name) {
        return errorDetailService.findByException(name);
    }

    // @desc Get errors by localDate
    // @route Get /api/error-details/exception/date/{date}
    // @access Public
    @GetMapping("/error-details/exception-by-date/{date}")
    public List<ErrorDetail> findByLocalDate(@PathVariable String date) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive().append(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toFormatter();
        LocalDate datetime = LocalDate.parse(date, formatter);
        return errorDetailService.findByLocalDate(datetime);
    }
}
