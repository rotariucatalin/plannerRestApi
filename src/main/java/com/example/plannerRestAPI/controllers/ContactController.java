package com.example.plannerRestAPI.controllers;

import com.example.plannerRestAPI.dtos.ContactDTO;
import com.example.plannerRestAPI.entities.Contact;
import com.example.plannerRestAPI.exceptions.ApiRequestException;
import com.example.plannerRestAPI.services.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<List<ContactDTO>> getAllContacts() {

        List<ContactDTO> allContacts = contactService.getAllContacts();
        return new ResponseEntity<>(allContacts, OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ContactDTO> getContact(@PathVariable(value = "id") int id) throws ApiRequestException {

        ContactDTO contact = contactService.getContact(id);
        return new ResponseEntity<>(contact, OK);
    }

    @PostMapping
    public ResponseEntity<ContactDTO> addNewContact(@RequestBody ContactDTO contactDTO) throws ApiRequestException {

        ContactDTO newContact = contactService.addNewContact(contactDTO);
        return new ResponseEntity<>(newContact, OK);
    }
}
