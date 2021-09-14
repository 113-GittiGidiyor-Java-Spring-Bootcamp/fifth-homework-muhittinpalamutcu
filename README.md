# School Management System

The school management system is an application that provides various types of endpoints to a user in order to hold students, instructors, and courses information easily and safely.

## Application Design

The system has been designed and built according to cover all the requirements. UML class diagram of the application is given below:

![UML-Class Diagram](https://github.com/113-GittiGidiyor-Java-Spring-Bootcamp/fourth-homework-muhittinpalamutcu/blob/main/SchoolManagementSystem-UML-Class-Diagram.png?raw=true)


## Services
The application provides 3 base URI for each property "instructor, course, and student". Some of these endpoints and their usage will be given below.

---

### 1- Student API endpoints
These endpoints allow a user to handle student CRUD operations.

#### - GET
```bash
/api/students                                //RETURN ALL THE STUDENTS
/api/students/{id}                          //RETURN STUDENT BY ID
/api/students/getByName/{name}             //RETURN ALL THE STUDENTS
/api/students/getGenderWithGrouping       //GET STUDENT GENDER WITH GROUPING
```

#### - POST
```bash
/api/students                           //SAVE STUDENT
```

#### - PUT
```bash
/api/students                          //UPDATE A STUDENT
```

#### - DELETE
```bash
/api/students/{id}                    //DELETE STUDENT BY ID
```

##### Example response GET /api/students/{id}
```
{
  "id": 3,
  "name": "John",
  "address": "London",
  "birthDate": "2001-03-10",
  "gender": "MALE",
  "courses": [
    {
      "id": 1,
      "name": "Programming 1",
      "courseCode": "SE115",
      "creditScore": 5
    }
  ]
}
```

##### Example POST request /api/students
```
{
    "name": "John",
    "address": "London",
    "birthDate": "1996-01-20",
    "gender": "MALE"
}
```
---

### 2- Course API endpoints
These endpoints allow a user to handle course CRUD operations as well as allow instructor registry and student registry through to course.

#### - GET
```bash
/api/courses                                 //RETURN ALL THE COURSES
/api/courses/{id}                           //RETURN STUDENT BY ID
/api/courses/getByName/{name}              //RETURN STUDENT BY NAME
```

#### - POST
```bash
/api/courses                            //SAVE COURSE
```

#### - PUT
```bash
/api/courses                           //UPDATE A COURSE
```

#### - DELETE
```bash
/api/courses/{id}                      //DELETE COURSE BY ID
/api/courses/deleteByName/{name}      //DELETE COURSE BY NAME
```

#### - PATCH
```bash
/api/courses/register-instructor/{courseCode} @RequestBody int instructorId //REGISTER INSTRUCTOR TO COURSE
/api/courses/register-student/{courseCode} @RequestBody int studentId  //REGISTER STUDENT TO COURSE
```

##### Example response GET /api/courses
```
[
  {
    "id": 1,
    "name": "Programming 1",
    "courseCode": "SE115",
    "creditScore": 5
  },
  {
    "id": 2,
    "name": "Programming 2",
    "courseCode": "SE116",
    "creditScore": 5
  },
  {
    "id": 3,
    "name": "Linear Algebra",
    "courseCode": "MATH250",
    "creditScore": 6
  }
]
```

##### Example POST request /api/courses
```
{
    "name": "System Programming",
    "courseCode": "SE375",
    "creditScore": 7
}
```
---

### 3- Instructor API endpoints
These endpoints allow a user to handle student CRUD operations.

#### - GET
```bash
/api/instructors                             //RETURN ALL THE INSTRUCTORS
/api/instructors/{id}                       //RETURN INSTRUCTOR BY ID
/api/instructors/getByName/{name}          //RETURN INSTRUCTOR BY NAME
```

#### - POST
```bash
/api/instructors/save-permanent-instructor       //SAVE PERMANENT INSTRUCTOR
/api/instructors/save-visiting-researcher       //SAVE VISITING RESEARCHER
```

#### - PUT
```bash
/api/instructors/save-permanent-instructor     //UPDATE PERMANENT INSTRUCTOR
/api/instructors/save-visiting-researcher     //UPDATE VISITING RESEARCHER
```

#### - DELETE
```bash
/api/instructors/{id}                        //DELETE INSTRUCTOR BY ID
/api/instructors/deleteByName/{name}        //DELETE INSTRUCTOR BY NAME
```

#### - PATCH
```bash
/api/instructors/{id}/increase-in-salary/{instructorType}  //INCREASE INSTRUCTOR SALARY
/api/instructors/{id}/reduce-in-salary/{instructorType}   //REDUCE INSTRUCTOR SALARY
```

##### Example response GET /api/instructors/{id}
```
{
  "id": 1,
  "name": "John",
  "address": "Amsterdam",
  "phoneNumber": "2521982912",
  "courses": [
    {
      "id": 1,
      "name": "Programming 1",
      "courseCode": "SE115",
      "creditScore": 5
    }
  ],
  "fixedSalary": 12000
}
```

##### Example POST request /api/instructors/save-permanent-instructor
```
{
  "name": "Jane",
  "address": "Berlin",
  "phoneNumber": "987623812",
  "fixedSalary": 15000
}
```
---

### 4- Instructor Salary Transaction API endpoints
These endpoints return salary transaction logs to users by specific fields.

#### - GET
```bash
/api/search-salary-transactions/{id}          //RETURN SALARY TRANSACTION TO GIVEN INSTRUCTOR ID
/api/api/search-salary-transactions/{date}   //RETURN SALARY TRANSACTION BY DATE
```

##### Example response GET /api/search-salary-transactions/{id} 
```
[
  {
    "id": 1,
    "instructorId": 5,
    "salaryBefore": 5000,
    "salaryAfter": 5100,
    "transactionAmount": 100,
    "clientIpAddress": "0:0:0:0:0:0:0:0",
    "clientUrl": "/api/instructors/5/increase-in-salary/visiting",
    "sessionActivityId": "7CD0A60A4BD45",
    "transactionDateTime": "2021-09-14",
    "transactionType": "INCREASE"
  },
  {
    "id": 2,
    "instructorId": 5,
    "salaryBefore": 5100,
    "salaryAfter": 5300,
    "transactionAmount": 200,
    "clientIpAddress": "0:0:0:0:0:0:0:0",
    "clientUrl": "/api/instructors/5/increase-in-salary/visiting",
    "sessionActivityId": "7CD0A60A4BD45",
    "transactionDateTime": "2021-09-14",
    "transactionType": "INCREASE"
  },
  {
    "id": 3,
    "instructorId": 5,
    "salaryBefore": 6100,
    "salaryAfter": 6050,
    "transactionAmount": 50,
    "clientIpAddress": "0:0:0:0:0:0:0:0",
    "clientUrl": "/api/instructors/5/reduce-in-salary/visiting",
    "sessionActivityId": "7CD0A60A4BD45",
    "transactionDateTime": "2021-09-14",
    "transactionType": "REDUCE"
  }
]
```

## Errors & Exceptions
* Student age can not be greater than 40 or less than 18, otherwise throw `StudentAgeNotValidException`
* There can't be 2 instructors with the same phone number in the system otherwise, throw `InstructorIsAlreadyExistException`
* There can't be 2 courses with the same course code in the system otherwise, throw `CourseIsAlreadyExistException`
* One course can take 20 students at most at the same time otherwise, throw `StudentNumberForOneCourseExceededException`

## Testing
According to project and its structure, unit testing was applied to controller and services layer of the app. For implementing unit testing JUnit framework was used. [JUnit](https://junit.org/junit4/)

Some of the Unit testing results can be seen below.

##

Course save test for service

Test | #1 | #2 | #3 | 
--- | --- | --- | --- |
Result | PASS | PASS | PASS |

Course save and update test for controller

Test | #1 | #2 | #3 | 
--- | --- | --- | --- |
Result | PASS | PASS | PASS |

Instructor save and update test for controller

Test | #1 | #2 | #3 | #4 | #5 | #6 | 
--- | --- | --- | --- | --- | --- | --- |
Result | PASS | PASS | PASS | PASS | PASS | PASS |

Student save test for service

Test | #1 | #2 |
--- | --- | --- |
Result | PASS | PASS |

Instructor save test for service

Test | #1 | #2 | #3 | #4 |
--- | --- | --- | --- | --- |
Result | PASS | PASS | PASS | PASS |

##
Sample test case for save course - service

```
@Test
    void saveCourseShouldGetCourseIsAlreadyExistException() {
        // given
        final String courseCode = "SE115";
        when(mockCourseRepository.findByCourseCode(courseCode)).thenReturn(new Course());

        // when
        CourseDTO dto = new CourseDTO();
        dto.setCourseCode(courseCode);
        
        Executable executable = () -> courseService.save(dto);
        // then
        assertThrows(CourseIsAlreadyExistException.class, executable);
    }
```

Sample test case for save course - service
```
@Test
    void saveCourseOk() {
        // given
        Course course = new Course(1, "Programming 1", "SE115", 5, new ArrayList<Student>(), new Instructor(), Instant.now(), Instant.now());
        CourseDTO dto = new CourseDTO(1, "Programming 1", "SE115", 5, new ArrayList<StudentDTO>(), new InstructorDTO());

        // when
        when(mockCourseRepository.findByCourseCode(course.getCourseCode())).thenReturn(null);
        when(mockCourseRepository.existsById(course.getId())).thenReturn(false);
        when(mockCourseMapper.mapFromCourseDTOtoCourse(dto)).thenReturn(course);
        when(mockCourseRepository.save(course)).thenReturn(course);

        Course actual = courseService.save(dto).get();

        // then
        assertNotNull(actual);
        assertEquals(course, actual);
    }
```

## License
[MIT](https://choosealicense.com/licenses/mit/)

---
# Beşinci hafta ödevi teslim tarihi : 14 Eylül Salı - Saat 23:00

![hm05](https://user-images.githubusercontent.com/45206582/132606840-bcc89ab7-37f4-4bbd-a950-227b838b0b3c.PNG)
