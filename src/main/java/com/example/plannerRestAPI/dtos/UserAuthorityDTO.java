package com.example.plannerRestAPI.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserAuthorityDTO {

    private int id;
    private String username;
    private List<AuthorityDTO> authorityList;

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

    public List<AuthorityDTO> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(List<AuthorityDTO> authorityList) {
        this.authorityList = authorityList;
    }
}
