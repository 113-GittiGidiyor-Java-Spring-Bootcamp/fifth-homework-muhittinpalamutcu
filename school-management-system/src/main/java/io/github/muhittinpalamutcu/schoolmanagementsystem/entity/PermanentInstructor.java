package io.github.muhittinpalamutcu.schoolmanagementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder(toBuilder = true)
public class PermanentInstructor extends Instructor {
    private double fixedSalary;
}
