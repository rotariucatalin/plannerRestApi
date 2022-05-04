package com.example.plannerRestAPI.services.implemntations;

import com.example.plannerRestAPI.dtos.BuildingDocumentationDTO;
import com.example.plannerRestAPI.dtos.ContactDTO;
import com.example.plannerRestAPI.dtos.UserDTO;
import com.example.plannerRestAPI.repositories.BuildingDocumentationRepository;
import com.example.plannerRestAPI.services.BuildingDocumentationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingDocumentationServiceImpl implements BuildingDocumentationService {

    private final BuildingDocumentationRepository buildingDocumentationRepository;

    public BuildingDocumentationServiceImpl(BuildingDocumentationRepository buildingDocumentationRepository) {
        this.buildingDocumentationRepository = buildingDocumentationRepository;
    }

    @Override
    public List<BuildingDocumentationDTO> getAllBuildingDocumentation() {

        List<BuildingDocumentationDTO> buildingDocumentationDTOS = new ArrayList<>();

        buildingDocumentationRepository.findAll().stream().forEach(buildingDocumentation -> {

            BuildingDocumentationDTO buildingDocumentationDTO = new BuildingDocumentationDTO();
            buildingDocumentationDTO.setId(buildingDocumentation.getId());
            buildingDocumentationDTO.setCategory(buildingDocumentation.getCategory());
            buildingDocumentationDTO.setSubCategory(buildingDocumentation.getSubCategory());
            buildingDocumentationDTO.setFileName(buildingDocumentation.getFileName());
            buildingDocumentationDTO.setFileDescription(buildingDocumentation.getFileDescription());
            buildingDocumentationDTO.setPathLocation(buildingDocumentation.getPathLocation());
            buildingDocumentationDTO.setAddedOn(buildingDocumentation.getAddedOn());

            UserDTO userDTO = new UserDTO();
            userDTO.setId(buildingDocumentation.getAddedBy().getContact().getId());

            ContactDTO contactDTO = new ContactDTO();
            contactDTO.setId(buildingDocumentation.getAddedBy().getContact().getId());
            contactDTO.setFirstName(buildingDocumentation.getAddedBy().getContact().getFirstName());
            contactDTO.setLastName(buildingDocumentation.getAddedBy().getContact().getLastName());

            userDTO.setContactDTO(contactDTO);

            buildingDocumentationDTO.setAddedBy(userDTO);

            buildingDocumentationDTOS.add(buildingDocumentationDTO);
        });

        return buildingDocumentationDTOS;
    }
}
