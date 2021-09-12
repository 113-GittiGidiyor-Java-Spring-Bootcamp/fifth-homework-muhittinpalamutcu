package io.github.muhittinpalamutcu.schoolmanagementsystem.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolManagementAppErrorResponse {
    private int status;
    private String message;
    private long timestamp;
}
