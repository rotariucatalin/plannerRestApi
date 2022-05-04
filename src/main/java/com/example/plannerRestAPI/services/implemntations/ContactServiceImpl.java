package com.example.plannerRestAPI.services.implemntations;

import com.example.plannerRestAPI.dtos.ContactDTO;
import com.example.plannerRestAPI.entities.Contact;
import com.example.plannerRestAPI.exceptions.ApiRequestException;
import com.example.plannerRestAPI.repositories.ContactRepository;
import com.example.plannerRestAPI.services.ContactService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<ContactDTO> getAllContacts() {

        List<ContactDTO> contactDTOList = contactRepository.findAll()
                .stream()
                .map( contact -> new ContactDTO(contact.getId(), contact.getFirstName(), contact.getLastName(), contact.getCity()) )
                .collect(Collectors.toList());

        return contactDTOList;

    }

    @Override
    public ContactDTO getContact(Integer id) throws ApiRequestException {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new ApiRequestException("No contact found"));
        ContactDTO contactDTO = new ContactDTO(contact.getId(), contact.getFirstName(), contact.getLastName(), contact.getCity());

        return contactDTO;
    }

    @Override
    public ContactDTO addNewContact(ContactDTO contactDTO) throws ApiRequestException {

        Contact contact = new Contact();
        contact.setFirstName(contactDTO.getFirstName());
        contact.setLastName(contactDTO.getLastName());
        contact.setCity(contactDTO.getCity());

        try {
            contactRepository.save(contact);
        } catch (Exception exception) {
            throw new ApiRequestException("Contact cannot be added. This is the error " + exception.getMessage());
        }

        contactDTO.setId(contact.getId());

        return contactDTO;
    }

    @Override
    public ContactDTO updateContact(ContactDTO contactDTO) throws ApiRequestException {

        if(contactDTO.getId() > 0) {

            Contact contact = contactRepository.findById(contactDTO.getId()).orElseThrow(() -> new ApiRequestException("No contact found"));
            contact.setLastName(contactDTO.getLastName());
            contact.setFirstName(contactDTO.getFirstName());
            contact.setCity(contactDTO.getCity());

            try {
                contactRepository.save(contact);
            } catch (Exception exception) {
                throw new ApiRequestException("The contact cannot be updated! This is the error" + exception.getMessage());
            }

        } else {
            throw new ApiRequestException("Permission denied to update the contact!");
        }

        return contactDTO;
    }

    @Override
    public void deleteContact(Integer id) throws ApiRequestException {
        contactRepository.findById(id).orElseThrow(() -> new ApiRequestException("No contact found"));
        try {
            contactRepository.deleteById(id);
        } catch (Exception exception) {
            throw new ApiRequestException("Contact cannot be deleted! This is the error " + exception.getMessage());
        }
    }
}
