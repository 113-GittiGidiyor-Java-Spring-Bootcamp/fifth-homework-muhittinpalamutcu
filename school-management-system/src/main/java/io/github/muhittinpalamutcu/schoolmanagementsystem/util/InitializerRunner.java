package io.github.muhittinpalamutcu.schoolmanagementsystem.util;

import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Course;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.PermanentInstructor;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Student;
import io.github.muhittinpalamutcu.schoolmanagementsystem.repository.CourseRepository;
import io.github.muhittinpalamutcu.schoolmanagementsystem.repository.InstructorRepository;
import io.github.muhittinpalamutcu.schoolmanagementsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitializerRunner implements CommandLineRunner {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    InstructorRepository instructorRepository;

    @Override
    public void run(String... args) throws Exception {
        studentRepository.deleteAll();

        Student student1 = studentRepository.save(Student.builder().name("Ali").address("Izmir").birthDate(LocalDate.of(1998, Month.JANUARY, 13)).gender("MALE").createdDate(Instant.now()).lastModifiedDate(Instant.now()).build());
        Student student2 = studentRepository.save(Student.builder().name("Ahmet").address("Istanbul").birthDate(LocalDate.of(1990, Month.JULY, 23)).gender("MALE").createdDate(Instant.now()).lastModifiedDate(Instant.now()).build());
        Student student3 = studentRepository.save(Student.builder().name("John").address("London").birthDate(LocalDate.of(2001, Month.MARCH, 10)).gender("MALE").createdDate(Instant.now()).lastModifiedDate(Instant.now()).build());
        Student student4 = studentRepository.save(Student.builder().name("Jane").address("Amsterdam").birthDate(LocalDate.of(1992, Month.APRIL, 18)).gender("FEMALE").createdDate(Instant.now()).lastModifiedDate(Instant.now()).build());
        Student student5 = studentRepository.save(Student.builder().name("Ayse").address("Antalya").birthDate(LocalDate.of(1974, Month.AUGUST, 19)).gender("FEMALE").createdDate(Instant.now()).lastModifiedDate(Instant.now()).build());

        List<Student> studentListForCourse1 = new ArrayList<>();
        studentListForCourse1.add(student1);
        studentListForCourse1.add(student2);
        studentListForCourse1.add(student3);
        studentListForCourse1.add(student4);

        courseRepository.save(Course.builder().name("Programming 1").courseCode("SE115").creditScore(5).students(studentListForCourse1).createdDate(Instant.now()).lastModifiedDate(Instant.now()).build());
        courseRepository.save(Course.builder().name("Programming 2").courseCode("SE116").creditScore(5).createdDate(Instant.now()).lastModifiedDate(Instant.now()).build());
        courseRepository.save(Course.builder().name("Linear Algebra").courseCode("MATH250").creditScore(6).createdDate(Instant.now()).lastModifiedDate(Instant.now()).build());

        instructorRepository.save(PermanentInstructor.builder().name("Koray").address("Istanbul").phoneNumber("52232112332").fixedSalary(12000).createdDate(Instant.now()).lastModifiedDate(Instant.now()).build());
        instructorRepository.save(PermanentInstructor.builder().name("Meltem").address("Izmir").phoneNumber("52232112367").fixedSalary(9000).createdDate(Instant.now()).lastModifiedDate(Instant.now()).build());
        instructorRepository.save(PermanentInstructor.builder().name("Volkan").address("Rotterdam").phoneNumber("52231272332").fixedSalary(10000).createdDate(Instant.now()).lastModifiedDate(Instant.now()).build());
        instructorRepository.save(PermanentInstructor.builder().name("Kuzey").address("Berlin").phoneNumber("52232112666").fixedSalary(7000).createdDate(Instant.now()).lastModifiedDate(Instant.now()).build());

    }
}
