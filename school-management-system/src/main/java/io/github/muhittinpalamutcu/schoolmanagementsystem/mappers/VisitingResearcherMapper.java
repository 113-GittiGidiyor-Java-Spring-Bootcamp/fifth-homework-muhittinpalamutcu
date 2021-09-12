package io.github.muhittinpalamutcu.schoolmanagementsystem.mappers;

import io.github.muhittinpalamutcu.schoolmanagementsystem.dto.VisitingResearcherDTO;
import io.github.muhittinpalamutcu.schoolmanagementsystem.entity.VisitingResearcher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VisitingResearcherMapper {
    VisitingResearcher mapFromVisitingResearcherDTOtoVisitingResearcher(VisitingResearcherDTO visitingResearcherDTO);

    VisitingResearcherDTO mapFromVisitingResearcherToVisitingResearcherDTO(VisitingResearcher visitingResearcher);
}
