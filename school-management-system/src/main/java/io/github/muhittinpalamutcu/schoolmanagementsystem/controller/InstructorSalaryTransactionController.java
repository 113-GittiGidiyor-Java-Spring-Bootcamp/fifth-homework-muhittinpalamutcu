package io.github.muhittinpalamutcu.schoolmanagementsystem.controller;

import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.InstructorSalaryTransactionLogger;
import io.github.muhittinpalamutcu.schoolmanagementsystem.service.InstructorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
public class InstructorSalaryTransactionController {

    private final InstructorServiceImpl instructorService;

    // @desc Get all salary transactions by instructor  id
    // @route Get /api/search-salary-transactions/{id}
    // @access Public
    @GetMapping("search-salary-transactions/{id}")
    public ResponseEntity<List<InstructorSalaryTransactionLogger>> searchSalaryTransactions(@PathVariable int id) {
        return new ResponseEntity<>(instructorService.searchSalaryTransactionsById(id), HttpStatus.OK);
    }

    // @desc Get all salary transactions by date
    // @route Get /api/search-salary-transactions/{date}
    // @access Public
    @GetMapping("/api/search-salary-transactions/{date}")
    public List<InstructorSalaryTransactionLogger> findByLocalDate(@PathVariable String date) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive().append(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toFormatter();
        LocalDate datetime = LocalDate.parse(date, formatter);
        return instructorService.searchSalaryTransactionsByDate(datetime);
    }

}
