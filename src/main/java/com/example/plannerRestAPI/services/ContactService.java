package com.example.plannerRestAPI.services;

import com.example.plannerRestAPI.dtos.ContactDTO;
import com.example.plannerRestAPI.entities.Contact;
import com.example.plannerRestAPI.exceptions.ApiRequestException;

import java.util.List;

public interface ContactService {

    List<ContactDTO> getAllContacts();
    ContactDTO getContact(Integer id) throws ApiRequestException;
    ContactDTO addNewContact(ContactDTO contactDTO) throws ApiRequestException;

}
