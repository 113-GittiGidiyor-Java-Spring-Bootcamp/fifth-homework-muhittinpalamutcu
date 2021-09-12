package io.github.muhittinpalamutcu.schoolmanagementsystem.service;

import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.StudentDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Student;
import io.github.muhittinpalamutcu.schoolmanagementsystem.exceptions.BadRequestException;
import io.github.muhittinpalamutcu.schoolmanagementsystem.exceptions.StudentAgeNotValidException;
import io.github.muhittinpalamutcu.schoolmanagementsystem.mappers.StudentMapper;
import io.github.muhittinpalamutcu.schoolmanagementsystem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    /**
     * This method return all the students exist in the database.
     *
     * @return List<Student>
     */
    @Override
    public List<Student> findAll() {
        return (List<Student>) studentRepository.findAll();
    }

    /**
     * This method return student by id.
     *
     * @param id
     * @return Student
     * @throws BadRequestException if student doesn't exist in the db.
     * @see BadRequestException
     */
    @Override
    public Student findById(int id) {
        if (!isExists(id)) {
            throw new BadRequestException("There is no student with id: " + id);
        }
        return studentRepository.findById(id).get();
    }

    /**
     * This method save student to database.
     *
     * @param studentDTO
     * @return Student
     * @throws BadRequestException         if student already exist in the db.
     * @throws StudentAgeNotValidException if student age greater than 40 or less than 18
     * @see BadRequestException
     * @see StudentAgeNotValidException
     */
    @Override
    @Transactional
    public Optional<Student> save(StudentDTO studentDTO) {
        if (isExists(studentDTO.getId())) {
            throw new BadRequestException("Student with id " + studentDTO.getId() + " is already exists!");
        }

        int year = Calendar.getInstance().get(Calendar.YEAR);
        int studentBirthYear = studentDTO.getBirthDate().getYear();

        if (year - studentBirthYear <= 18 || year - studentBirthYear >= 40) {
            throw new StudentAgeNotValidException("Student age can not be greater than 40 or not be less than 18");
        }

        Student student = studentMapper.mapFromStudentDTOtoStudent(studentDTO);
        return Optional.of(studentRepository.save(student));
    }

    /**
     * This method delete student by id.
     *
     * @param id
     * @throws BadRequestException if student doesn't exist in db.
     * @see BadRequestException
     */
    @Override
    @Transactional
    public void deleteById(int id) {
        if (!isExists(id)) {
            throw new BadRequestException("There is no student with id: " + id);
        }
        studentRepository.deleteById(id);
    }

    /**
     * This method update existed Student.
     *
     * @param studentDTO
     * @return Student
     * @throws BadRequestException if student doesn't exist in db.
     * @see BadRequestException
     */
    @Override
    @Transactional
    public Student update(StudentDTO studentDTO) {
        if (!isExists(studentDTO.getId())) {
            throw new BadRequestException("There is no student with id: " + studentDTO.getId());
        }
        Student student = studentMapper.mapFromStudentDTOtoStudent(studentDTO);
        return studentRepository.save(student);
    }

    /**
     * This method return a student by name
     *
     * @param name
     * @return Student
     * @throws BadRequestException if student doesn't exist in db.
     * @see BadRequestException
     */
    @Override
    public Student findByName(String name) {
        Student student = studentRepository.findByName(name);
        if (student == null) {
            throw new BadRequestException("There is no student with name: " + name);
        }
        return studentRepository.findByName(name);
    }

    /**
     * This method return student gender with grouping.
     *
     * @param "args Unused."
     * @return List
     */
    public List<?> getGenderWithGrouping() {
        return studentRepository.getGenderWithGrouping();
    }

    /**
     * This method delete student by name
     *
     * @param name
     * @throws BadRequestException if student doesn't exist in db.
     * @see BadRequestException
     */
    @Override
    @Transactional
    public void deleteByName(String name) {
        Student student = studentRepository.findByName(name);
        if (student == null) {
            throw new BadRequestException("There is no student with name: " + name);
        }

        studentRepository.deleteStudentByName(name);
    }

    /**
     * This method check if course exist in db.
     *
     * @param id
     * @return boolean
     */
    public boolean isExists(int id) {
        return studentRepository.existsById(id);
    }
}
