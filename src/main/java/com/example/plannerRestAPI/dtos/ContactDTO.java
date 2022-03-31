package com.example.plannerRestAPI.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String city;
    private UserDTO user;

    public ContactDTO() {
    }

    public ContactDTO(int id, String firstName, String lastName, String city) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
