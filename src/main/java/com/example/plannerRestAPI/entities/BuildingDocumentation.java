package com.example.plannerRestAPI.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "building_documentation")
public class BuildingDocumentation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String category;
    @Column(name = "subcategory")
    private String subCategory;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "file_description")
    private String fileDescription;
    @Column(name = "location_path")
    private String pathLocation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "added_by")
    private User addedBy;
    private LocalDate addedOn;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deleted_by")
    private User deletedBy;
    private LocalDate deletedOn;
    private boolean deleted;

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

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }

    public LocalDate getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDate addedOn) {
        this.addedOn = addedOn;
    }

    public User getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(User deletedBy) {
        this.deletedBy = deletedBy;
    }

    public LocalDate getDeletedOn() {
        return deletedOn;
    }

    public void setDeletedOn(LocalDate deletedOn) {
        this.deletedOn = deletedOn;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
