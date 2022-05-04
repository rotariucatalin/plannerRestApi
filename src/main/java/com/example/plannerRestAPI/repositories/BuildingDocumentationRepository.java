package com.example.plannerRestAPI.repositories;

import com.example.plannerRestAPI.entities.BuildingDocumentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingDocumentationRepository extends JpaRepository<BuildingDocumentation,Integer> {

}
