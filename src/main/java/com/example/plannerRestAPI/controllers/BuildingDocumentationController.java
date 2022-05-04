package com.example.plannerRestAPI.controllers;

import com.example.plannerRestAPI.dtos.BuildingDocumentationDTO;
import com.example.plannerRestAPI.services.BuildingDocumentationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/building_documentation")
public class BuildingDocumentationController {

    private final BuildingDocumentationService buildingDocumentationService;

    public BuildingDocumentationController(BuildingDocumentationService buildingDocumentationService) {
        this.buildingDocumentationService = buildingDocumentationService;
    }

    @GetMapping
    public ResponseEntity<List<BuildingDocumentationDTO>> getAllBuildingDocumentation() {

        List<BuildingDocumentationDTO>buildingDocumentationDTOS = buildingDocumentationService.getAllBuildingDocumentation();

        return new ResponseEntity<>(buildingDocumentationDTOS, OK);
    }
}
