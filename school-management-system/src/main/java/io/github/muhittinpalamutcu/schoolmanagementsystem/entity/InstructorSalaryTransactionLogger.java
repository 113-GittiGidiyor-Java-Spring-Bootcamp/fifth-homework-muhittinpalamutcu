package io.github.muhittinpalamutcu.schoolmanagementsystem.entity;

import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.enumeration.SalaryTransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class InstructorSalaryTransactionLogger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int instructorId;
    private double salaryBefore;
    private double salaryAfter;
    private double transactionAmount;
    private String clientIpAddress;
    private String clientUrl;
    private String sessionActivityId;
    private LocalDate transactionDateTime;
    @Enumerated(EnumType.STRING)
    private SalaryTransactionType transactionType;
}
