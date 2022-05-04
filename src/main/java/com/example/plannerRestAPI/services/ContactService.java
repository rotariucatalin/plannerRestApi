package com.example.plannerRestAPI.services;

import com.example.plannerRestAPI.dtos.ContactDTO;
import com.example.plannerRestAPI.exceptions.ApiRequestException;

import java.util.List;

public interface ContactService {

    List<ContactDTO> getAllContacts();
    ContactDTO getContact(Integer id) throws ApiRequestException;
    ContactDTO addNewContact(ContactDTO contactDTO) throws ApiRequestException;
    ContactDTO updateContact(ContactDTO contactDTO) throws ApiRequestException;
    void deleteContact(Integer id) throws ApiRequestException;
}
