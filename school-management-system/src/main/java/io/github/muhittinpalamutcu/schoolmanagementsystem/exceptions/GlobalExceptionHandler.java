package io.github.muhittinpalamutcu.schoolmanagementsystem.exceptions;

import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.ErrorDetail;
import io.github.muhittinpalamutcu.schoolmanagementsystem.service.ErrorDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @Autowired
    private ErrorDetailService errorDetailService;

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<SchoolManagementAppErrorResponse> handleException(BadRequestException exception) {
        SchoolManagementAppErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());

        saveErrorDetailToDB(exception.getClass().getSimpleName(), response.getStatus(), response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({StudentAgeNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<SchoolManagementAppErrorResponse> handleException(StudentAgeNotValidException exception) {
        SchoolManagementAppErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
        saveErrorDetailToDB(exception.getClass().getSimpleName(), response.getStatus(), response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InstructorIsAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<SchoolManagementAppErrorResponse> handleException(InstructorIsAlreadyExistException exception) {
        SchoolManagementAppErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
        saveErrorDetailToDB(exception.getClass().getSimpleName(), response.getStatus(), response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({CourseIsAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<SchoolManagementAppErrorResponse> handleException(CourseIsAlreadyExistException exception) {
        SchoolManagementAppErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
        saveErrorDetailToDB(exception.getClass().getSimpleName(), response.getStatus(), response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({StudentNumberForOneCourseExceededException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<SchoolManagementAppErrorResponse> handleException(StudentNumberForOneCourseExceededException exception) {
        SchoolManagementAppErrorResponse response = prepareErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
        saveErrorDetailToDB(exception.getClass().getSimpleName(), response.getStatus(), response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private SchoolManagementAppErrorResponse prepareErrorResponse(HttpStatus httpStatus, String message) {
        SchoolManagementAppErrorResponse response = new SchoolManagementAppErrorResponse();
        response.setStatus(httpStatus.value());
        response.setMessage(message);
        response.setTimestamp(System.currentTimeMillis());
        return response;
    }

    private void saveErrorDetailToDB(String exceptionName, int status, String message) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setException(exceptionName);
        errorDetail.setStatus(status);
        errorDetail.setMessage(message);
        errorDetail.setLocalDate(LocalDate.now());
        errorDetailService.save(errorDetail);
    }
}
