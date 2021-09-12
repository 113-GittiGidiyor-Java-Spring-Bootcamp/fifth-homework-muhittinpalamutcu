package io.github.muhittinpalamutcu.schoolmanagementsystem.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PermanentInstructorDTO extends InstructorDTO {
    @ApiModelProperty(example = "5000")
    @NotNull(message = "Salary is mandatory")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private double fixedSalary;
}
