package io.github.muhittinpalamutcu.schoolmanagementsystem.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    //User hidden property for hiding sensitive data
    @ApiModelProperty(hidden = true)
    private int id;

    //Validate name while taking name field
    @ApiModelProperty(example = "Programming 1")
    @NotBlank(message = "Course name is mandatory")
    private String name;

    @ApiModelProperty(example = "SE115")
    @NotBlank(message = "Course code is mandatory")
    private String courseCode;

    @ApiModelProperty(example = "5")
    @NotNull(message = "Credit score is mandatory")
    private int creditScore;

    private List<StudentDTO> students = new ArrayList<>();
    private InstructorDTO instructorDTO;
}
