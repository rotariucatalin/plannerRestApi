package com.example.plannerRestAPI.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorityDTO {

    private int id;
    private String name;
    private List<UserDTO> userDTOList;

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

    public List<UserDTO> getUserDTOList() {
        return userDTOList;
    }

    public void setUserDTOList(List<UserDTO> userDTOList) {
        this.userDTOList = userDTOList;
    }

    @Override
    public String toString() {
        return "AuthorityDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userDTOList=" + userDTOList +
                '}';
    }
}
