package io.github.muhittinpalamutcu.schoolmanagementsystem.mappers;

import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.StudentDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Student;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    Student mapFromStudentDTOtoStudent(StudentDTO studentDTO);

    StudentDTO mapFromStudentToStudentDTO(Student student);

    List<StudentDTO> mapListFromStudentToStudentDTO(List<Student> studentList);
}
