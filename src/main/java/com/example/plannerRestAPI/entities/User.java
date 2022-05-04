package com.example.plannerRestAPI.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String email;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_authority", referencedColumnName = "id")
    )
    private List<Authority> authorities;
    @OneToOne
    @JoinTable(name = "user_contact",
            joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_contact", referencedColumnName = "id"))
    private Contact contact;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "added_by", referencedColumnName = "id")
    private List<BuildingDocumentation> buildingDocumentationList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public List<BuildingDocumentation> getBuildingDocumentationList() {
        return buildingDocumentationList;
    }

    public void setBuildingDocumentationList(List<BuildingDocumentation> buildingDocumentationList) {
        this.buildingDocumentationList = buildingDocumentationList;
    }
}
