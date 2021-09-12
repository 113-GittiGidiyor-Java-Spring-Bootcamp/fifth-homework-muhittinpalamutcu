package io.github.muhittinpalamutcu.schoolmanagementsystem.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitingResearcherDTO extends InstructorDTO {
    @ApiModelProperty(example = "5000")
    @NotNull(message = "Salary is mandatory")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private double hourlySalary;
}
