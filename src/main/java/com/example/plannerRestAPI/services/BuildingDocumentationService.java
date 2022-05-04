package com.example.plannerRestAPI.services;

import com.example.plannerRestAPI.dtos.BuildingDocumentationDTO;

import java.util.List;


public interface BuildingDocumentationService {

    List<BuildingDocumentationDTO> getAllBuildingDocumentation();

}
