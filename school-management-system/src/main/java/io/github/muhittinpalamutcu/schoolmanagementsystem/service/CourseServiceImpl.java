package io.github.muhittinpalamutcu.schoolmanagementsystem.service;

import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.CourseDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Course;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Instructor;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Student;
import io.github.muhittinpalamutcu.schoolmanagementsystem.exceptions.BadRequestException;
import io.github.muhittinpalamutcu.schoolmanagementsystem.exceptions.CourseIsAlreadyExistException;
import io.github.muhittinpalamutcu.schoolmanagementsystem.exceptions.StudentNumberForOneCourseExceededException;
import io.github.muhittinpalamutcu.schoolmanagementsystem.mappers.CourseMapper;
import io.github.muhittinpalamutcu.schoolmanagementsystem.repository.CourseRepository;
import io.github.muhittinpalamutcu.schoolmanagementsystem.repository.InstructorRepository;
import io.github.muhittinpalamutcu.schoolmanagementsystem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private InstructorRepository instructorRepository;

    /**
     * This method return all the courses exist the database.
     *
     * @param "args Unused."
     * @return List<Course>
     */
    @Override
    public List<Course> findAll() {
        return (List<Course>) courseRepository.findAll();
    }

    /**
     * This method find the specific course by its id.
     *
     * @param id
     * @return Course
     * @throws BadRequestException On input error.
     * @see BadRequestException
     */
    @Override
    public Course findById(int id) {
        if (!isExists(id)) {
            throw new BadRequestException("There is no course with id: " + id);
        }
        return courseRepository.findById(id).get();
    }

    /**
     * This method save course to database.
     *
     * @param courseDTO
     * @return Course
     * @throws CourseIsAlreadyExistException if input course is already exist in db.
     * @see CourseIsAlreadyExistException
     */
    @Override
    @Transactional
    public Optional<Course> save(CourseDTO courseDTO) {
        if (courseRepository.findByCourseCode(courseDTO.getCourseCode()) != null) {
            throw new CourseIsAlreadyExistException("Course with code " + courseDTO.getCourseCode() + " is already exists!");
        }

        if (isExists(courseDTO.getId())) {
            throw new CourseIsAlreadyExistException("Course with id " + courseDTO.getId() + " is already exists!");
        }

        Course course = courseMapper.mapFromCourseDTOtoCourse(courseDTO);
        return Optional.of(courseRepository.save(course));
    }

    /**
     * This method delete a course by its id.
     *
     * @param id
     * @throws BadRequestException if course doesn't exist in db.
     * @see BadRequestException
     */
    @Override
    @Transactional
    public void deleteById(int id) {
        if (!isExists(id)) {
            throw new BadRequestException("There is no course with id: " + id);
        }
        courseRepository.deleteById(id);
    }

    /**
     * This method update existed course.
     *
     * @param courseDTO
     * @return Course
     * @throws BadRequestException if course doesn't exist in db.
     * @see BadRequestException
     */
    @Override
    @Transactional
    public Course update(CourseDTO courseDTO) {
        if (!isExists(courseDTO.getId())) {
            throw new BadRequestException("There is no course with id " + courseDTO.getId());
        }
        Course course = courseMapper.mapFromCourseDTOtoCourse(courseDTO);
        return courseRepository.save(course);
    }

    /**
     * This method return a course by its name.
     *
     * @param name
     * @return Course
     * @throws BadRequestException if course doesn't exist in db.
     * @see BadRequestException
     */
    @Override
    public Course findByName(String name) {
        Course course = courseRepository.findByName(name);
        if (course == null) {
            throw new BadRequestException("There is no course with name: " + name);
        }
        return courseRepository.findByName(name);
    }

    /**
     * This method delete the course by its name
     *
     * @param name
     * @throws BadRequestException if course doesn't exist in db.
     */
    @Override
    @Transactional
    public void deleteByName(String name) {
        Course course = courseRepository.findByName(name);
        if (course == null) {
            throw new BadRequestException("There is no course with name: " + name);
        }
        courseRepository.deleteCourseByName(name);
    }

    /**
     * This method register student to a course.
     *
     * @param courseCode
     * @param studentId
     * @return Course
     * @throws BadRequestException if student or course doesn't exist in db.
     * @see BadRequestException
     */
    @Transactional
    public Optional<Course> registerStudentToCourse(String courseCode, int studentId) {
        Course course = courseRepository.findByCourseCode(courseCode);
        if (course == null) {
            throw new BadRequestException("There is no course with code: " + courseCode);
        }

        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (!optionalStudent.isPresent()) {
            throw new BadRequestException("There is no student with id: " + studentId);
        }
        Student student = optionalStudent.get();

        List<Student> students = course.getStudents();
        if (students.size() < 20) {
            students.add(student);
            course.setStudents(students);
            return Optional.of(courseRepository.save(course));
        } else {
            throw new StudentNumberForOneCourseExceededException("Students number can not exceed 20 - current students number enrolled in course: " + students.size());
        }
    }

    /**
     * This method register instructor to a course.
     *
     * @param courseCode
     * @param instructorId
     * @return Course
     * @throws BadRequestException if instructor or course doesn't exist in db.
     * @see BadRequestException
     */
    public Optional<Course> registerInstructorToCourse(String courseCode, int instructorId) {
        Course course = courseRepository.findByCourseCode(courseCode);
        if (course == null) {
            throw new BadRequestException("There is no course with code: " + courseCode);
        }

        Optional<Instructor> optionalInstructor = instructorRepository.findById(instructorId);
        if (!optionalInstructor.isPresent()) {
            throw new BadRequestException("There is no instructor with id: " + instructorId);
        }

        Instructor instructor = optionalInstructor.get();
        course.setInstructor(instructor);
        return Optional.of(courseRepository.save(course));
    }

    /**
     * This method check if course exist in db.
     *
     * @param id
     * @return boolean
     */
    public boolean isExists(int id) {
        return courseRepository.existsById(id);
    }
}
