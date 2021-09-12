package io.github.muhittinpalamutcu.schoolmanagementsystem.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorDTO {
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

    @ApiModelProperty(example = "02321233265")
    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;

    private List<CourseDTO> courseDTOList = new ArrayList<>();
}
