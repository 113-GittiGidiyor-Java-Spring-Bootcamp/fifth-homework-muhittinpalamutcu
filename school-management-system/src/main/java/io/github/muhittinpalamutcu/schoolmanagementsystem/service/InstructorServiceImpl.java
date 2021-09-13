package io.github.muhittinpalamutcu.schoolmanagementsystem.service;

import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.InstructorDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.PermanentInstructorDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.VisitingResearcherDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Instructor;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.InstructorSalaryTransactionLogger;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.PermanentInstructor;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.VisitingResearcher;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.enumeration.SalaryTransactionType;
import io.github.muhittinpalamutcu.schoolmanagementsystem.exceptions.BadRequestException;
import io.github.muhittinpalamutcu.schoolmanagementsystem.exceptions.InstructorIsAlreadyExistException;
import io.github.muhittinpalamutcu.schoolmanagementsystem.mappers.PermanentInstructorMapper;
import io.github.muhittinpalamutcu.schoolmanagementsystem.mappers.VisitingResearcherMapper;
import io.github.muhittinpalamutcu.schoolmanagementsystem.repository.InstructorRepository;
import io.github.muhittinpalamutcu.schoolmanagementsystem.repository.InstructorSalaryTransactionRepository;
import io.github.muhittinpalamutcu.schoolmanagementsystem.util.ClientRequestInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private PermanentInstructorMapper permanentInstructorMapper;
    @Autowired
    private VisitingResearcherMapper visitingResearcherMapper;
    @Autowired
    private InstructorSalaryTransactionRepository instructorSalaryTransactionLoggerRepository;
    @Autowired
    private ClientRequestInfo clientRequestInfo;


    /**
     * This method return all the instructors exist in the database.
     *
     * @return List<Instructor>
     */
    @Override
    public List<Instructor> findAll() {
        return (List<Instructor>) instructorRepository.findAll();
    }

    /**
     * This method return instructor by id.
     *
     * @param id
     * @return Instructor
     * @throws BadRequestException if instructor doesn't exist in db.
     * @see BadRequestException
     */
    @Override
    public Instructor findById(int id) {
        if (!isExists(id)) {
            throw new BadRequestException("There is no instructor with id: " + id);
        }
        return instructorRepository.findById(id).get();
    }

    /**
     * This method save instructor to database.
     *
     * @param instructorDTO
     * @return Instructor
     * @throws InstructorIsAlreadyExistException if input instructor is already exist in db.
     * @see InstructorIsAlreadyExistException
     */
    @Override
    @Transactional
    public Optional<Instructor> save(InstructorDTO instructorDTO) {
        if (instructorRepository.findByPhoneNumber(instructorDTO.getPhoneNumber()) != null) {
            throw new InstructorIsAlreadyExistException("This phone number is already belong to another instructor info: " + instructorDTO.getPhoneNumber());
        }

        if (isExists(instructorDTO.getId())) {
            throw new BadRequestException("Instructor with id " + instructorDTO.getId() + " is already exists!");
        } else {
            if (instructorDTO instanceof PermanentInstructorDTO) {
                PermanentInstructor permanentInstructor = permanentInstructorMapper.mapFromPermanentInstructorDTOtoPermanentInstructor((PermanentInstructorDTO) instructorDTO);
                return Optional.of(instructorRepository.save(permanentInstructor));
            } else if (instructorDTO instanceof VisitingResearcherDTO) {
                VisitingResearcher visitingResearcher = visitingResearcherMapper.mapFromVisitingResearcherDTOtoVisitingResearcher((VisitingResearcherDTO) instructorDTO);
                return Optional.of(instructorRepository.save(visitingResearcher));
            } else {
                throw new BadRequestException("Unknown type");
            }
        }
    }

    /**
     * This method delete instructor by id.
     *
     * @param id
     * @throws BadRequestException if instructor doesn't exist in db.
     * @see BadRequestException
     */
    @Override
    @Transactional
    public void deleteById(int id) {
        if (!isExists(id)) {
            throw new BadRequestException("There is no instructor with id: " + id);
        }
        instructorRepository.deleteById(id);
    }

    /**
     * This method update existed Instructor.
     *
     * @param instructorDTO
     * @return Instructor
     * @throws BadRequestException if instructor doesn't exist in db.
     * @see BadRequestException
     */
    @Override
    @Transactional
    public Instructor update(InstructorDTO instructorDTO) {
        if (!isExists(instructorDTO.getId())) {
            throw new BadRequestException("There is no instructor with id: " + instructorDTO.getId());
        } else {
            if (instructorDTO instanceof PermanentInstructorDTO) {
                PermanentInstructor permanentInstructor = permanentInstructorMapper.mapFromPermanentInstructorDTOtoPermanentInstructor((PermanentInstructorDTO) instructorDTO);
                return instructorRepository.save(permanentInstructor);
            } else if (instructorDTO instanceof VisitingResearcherDTO) {
                VisitingResearcher visitingResearcher = visitingResearcherMapper.mapFromVisitingResearcherDTOtoVisitingResearcher((VisitingResearcherDTO) instructorDTO);
                return instructorRepository.save(visitingResearcher);
            } else {
                throw new BadRequestException("Unknown type");
            }
        }
    }

    /**
     * This method return instructor by name.
     *
     * @param name
     * @return Instructor
     * @throws BadRequestException if instructor doesn't exist in db.
     * @see BadRequestException
     */
    @Override
    public Instructor findByName(String name) {
        Instructor instructor = instructorRepository.findByName(name);
        if (instructor == null) {
            throw new BadRequestException("There is no instructor with name: " + name);
        }
        return instructorRepository.findByName(name);
    }

    /**
     * This method delete instructor by name.
     *
     * @param name
     * @throws BadRequestException if instructor doesn't exist in db.
     * @see BadRequestException
     */
    @Override
    @Transactional
    public void deleteByName(String name) {
        Instructor instructor = instructorRepository.findByName(name);
        if (instructor == null) {
            throw new BadRequestException("There is no instructor with name: " + name);
        }
        instructorRepository.deleteInstructorByName(name);
    }

    /**
     * This method increase the salary of selected instructor
     *
     * @param id
     * @param amount
     * @param instructorType
     * @return Instructor
     * @throws BadRequestException if instructor doesn't exist in db.
     * @see BadRequestException
     */
    public Instructor increaseInSalary(int id, int amount, String instructorType) {
        Optional<Instructor> optionalInstructor = instructorRepository.findById(id);
        if (!optionalInstructor.isPresent()) {
            throw new BadRequestException("There is no instructor with id: " + id);
        }

        if (instructorType.equals("permanent")) {
            PermanentInstructor permanentInstructor = (PermanentInstructor) optionalInstructor.get();
            saveTransactionToDatabase(permanentInstructor, amount, SalaryTransactionType.INCREASE);
            permanentInstructor.setFixedSalary(permanentInstructor.getFixedSalary() + amount);
            return instructorRepository.save(permanentInstructor);
        } else {
            VisitingResearcher visitingResearcher = (VisitingResearcher) optionalInstructor.get();
            saveTransactionToDatabase(visitingResearcher, amount, SalaryTransactionType.INCREASE);
            visitingResearcher.setHourlySalary(visitingResearcher.getHourlySalary() + amount);
            return instructorRepository.save(visitingResearcher);
        }
    }

    /**
     * This method reduce the salary of selected instructor
     *
     * @param id
     * @param amount
     * @param instructorType
     * @return Instructor
     * @throws BadRequestException if instructor doesn't exist in db.
     * @see BadRequestException
     */
    public Instructor reduceInSalary(int id, int amount, String instructorType) {
        Optional<Instructor> optionalInstructor = instructorRepository.findById(id);
        if (!optionalInstructor.isPresent()) {
            throw new BadRequestException("There is no instructor with id: " + id);
        }

        if (instructorType.equals("permanent")) {
            PermanentInstructor permanentInstructor = (PermanentInstructor) optionalInstructor.get();
            saveTransactionToDatabase(permanentInstructor, amount, SalaryTransactionType.REDUCE);
            permanentInstructor.setFixedSalary(permanentInstructor.getFixedSalary() - amount);
            return instructorRepository.save(permanentInstructor);
        } else {
            VisitingResearcher visitingResearcher = (VisitingResearcher) optionalInstructor.get();
            saveTransactionToDatabase(visitingResearcher, amount, SalaryTransactionType.REDUCE);
            visitingResearcher.setHourlySalary(visitingResearcher.getHourlySalary() - amount);
            return instructorRepository.save(visitingResearcher);
        }
    }

    /**
     * This method returns the salary transactions according to instructor id
     *
     * @param id
     * @return List<InstructorSalaryTransactionLogger>
     * @throws BadRequestException if instructor doesn't exist in db.
     * @see BadRequestException
     */
    public List<InstructorSalaryTransactionLogger> searchSalaryTransactionsById(int id) {
        if (!isExists(id)) {
            throw new BadRequestException("There is no instructor with id: " + id);
        }
        return instructorSalaryTransactionLoggerRepository.findByInstructorId(id);
    }

    /**
     * This method returns the salary transactions according to date
     *
     * @param localDate
     * @return List<InstructorSalaryTransactionLogger>
     */
    public List<InstructorSalaryTransactionLogger> searchSalaryTransactionsByDate(LocalDate localDate) {
        return instructorSalaryTransactionLoggerRepository.findByTransactionDateTime(localDate);
    }

    /**
     * This method check if instructor exist in db.
     *
     * @param id
     * @return boolean
     */
    public boolean isExists(int id) {
        return instructorRepository.existsById(id);
    }

    /**
     * This method saves the transaction operation for instructor
     *
     * @param instructor
     * @param amount
     * @param transactionType
     */
    private void saveTransactionToDatabase(Instructor instructor, double amount, SalaryTransactionType transactionType) {
        InstructorSalaryTransactionLogger transactionLogger = new InstructorSalaryTransactionLogger();
        transactionLogger.setInstructorId(instructor.getId());
        if (transactionType.equals(SalaryTransactionType.INCREASE)) {
            if (instructor instanceof PermanentInstructor) {
                PermanentInstructor permanentInstructor = (PermanentInstructor) instructor;
                transactionLogger.setSalaryBefore(permanentInstructor.getFixedSalary());
                transactionLogger.setSalaryAfter(permanentInstructor.getFixedSalary() + amount);
            } else {
                VisitingResearcher visitingResearcher = (VisitingResearcher) instructor;
                transactionLogger.setSalaryBefore(visitingResearcher.getHourlySalary());
                transactionLogger.setSalaryAfter(visitingResearcher.getHourlySalary() + amount);
            }
        } else {
            if (instructor instanceof PermanentInstructor) {
                PermanentInstructor permanentInstructor = (PermanentInstructor) instructor;
                transactionLogger.setSalaryBefore(permanentInstructor.getFixedSalary());
                transactionLogger.setSalaryAfter(permanentInstructor.getFixedSalary() - amount);
            } else {
                VisitingResearcher visitingResearcher = (VisitingResearcher) instructor;
                transactionLogger.setSalaryBefore(visitingResearcher.getHourlySalary());
                transactionLogger.setSalaryAfter(visitingResearcher.getHourlySalary() - amount);
            }
        }

        transactionLogger.setTransactionAmount(amount);
        transactionLogger.setTransactionDateTime(LocalDate.now());
        transactionLogger.setClientUrl(clientRequestInfo.getClientUrl());
        transactionLogger.setClientIpAddress(clientRequestInfo.getClientIpAddress());
        transactionLogger.setSessionActivityId(clientRequestInfo.getSessionActivityId());
        transactionLogger.setTransactionType(transactionType);
        this.instructorSalaryTransactionLoggerRepository.save(transactionLogger);
    }
}
