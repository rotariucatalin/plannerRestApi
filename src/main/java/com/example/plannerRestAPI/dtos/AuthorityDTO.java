package com.example.plannerRestAPI.dtos;

public class AuthorityDTO {

    private int id;
    private String name;

    public AuthorityDTO() {
    }

    public AuthorityDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
