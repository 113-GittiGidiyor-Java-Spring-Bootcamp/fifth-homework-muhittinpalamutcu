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

## Errors & Exceptions
* Student age can not be greater than 40 or less than 18, otherwise throw `StudentAgeNotValidException`
* There can't be 2 instructors with the same phone number in the system otherwise, throw `InstructorIsAlreadyExistException`
* There can't be 2 courses with the same course code in the system otherwise, throw `CourseIsAlreadyExistException`
* One course can take 20 students at most at the same time otherwise, throw `StudentNumberForOneCourseExceededException`

## License
[MIT](https://choosealicense.com/licenses/mit/)

---
# Beşinci hafta ödevi teslim tarihi : 14 Eylül Salı - Saat 23:00

![hm05](https://user-images.githubusercontent.com/45206582/132606840-bcc89ab7-37f4-4bbd-a950-227b838b0b3c.PNG)
