package com.example.plannerRestAPI.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BuildingDocumentationDTO {

    private int id;
    private String category;
    private String subCategory;
    private String fileName;
    private String fileDescription;
    private String pathLocation;
    private UserDTO addedBy;
    private LocalDate addedOn;
    private Boolean deleted;
    private UserDTO deletedBy;
    private LocalDate deletedOn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    public String getPathLocation() {
        return pathLocation;
    }

    public void setPathLocation(String pathLocation) {
        this.pathLocation = pathLocation;
    }

    public UserDTO getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(UserDTO addedBy) {
        this.addedBy = addedBy;
    }

    public LocalDate getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDate addedOn) {
        this.addedOn = addedOn;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public UserDTO getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(UserDTO deletedBy) {
        this.deletedBy = deletedBy;
    }

    public LocalDate getDeletedOn() {
        return deletedOn;
    }

    public void setDeletedOn(LocalDate deletedOn) {
        this.deletedOn = deletedOn;
    }
}
