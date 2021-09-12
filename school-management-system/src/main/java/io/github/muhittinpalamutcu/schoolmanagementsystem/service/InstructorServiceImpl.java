package io.github.muhittinpalamutcu.schoolmanagementsystem.service;

import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.InstructorDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.PermanentInstructorDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.VisitingResearcherDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.Instructor;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.PermanentInstructor;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.VisitingResearcher;
import io.github.muhittinpalamutcu.schoolmanagementsystem.exceptions.BadRequestException;
import io.github.muhittinpalamutcu.schoolmanagementsystem.exceptions.InstructorIsAlreadyExistException;
import io.github.muhittinpalamutcu.schoolmanagementsystem.mappers.PermanentInstructorMapper;
import io.github.muhittinpalamutcu.schoolmanagementsystem.mappers.VisitingResearcherMapper;
import io.github.muhittinpalamutcu.schoolmanagementsystem.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * This method check if instructor exist in db.
     *
     * @param id
     * @return boolean
     */
    public boolean isExists(int id) {
        return instructorRepository.existsById(id);
    }
}
