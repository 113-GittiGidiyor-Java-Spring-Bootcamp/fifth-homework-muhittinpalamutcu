package io.github.muhittinpalamutcu.schoolmanagementsystem.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    //Use hidden property for hiding sensitive data
    @ApiModelProperty(hidden = true)
    private int id;

    //Validate name
    @ApiModelProperty(example = "Muhittin")
    @NotBlank(message = "Instructor name is mandatory")
    private String name;

    @ApiModelProperty(example = "Izmir, Turkey")
    @NotBlank(message = "Address is mandatory")
    private String address;

    @ApiModelProperty(example ="1998-01-13" )
    @NotNull(message = "BirthDate is mandatory")
    private LocalDate birthDate;

    @ApiModelProperty(example = "MALE")
    @NotBlank(message = "Gender is mandatory")
    private String gender;

    private List<CourseDTO> courseDTOList = new ArrayList<>();
}
