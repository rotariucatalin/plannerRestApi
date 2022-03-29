package com.example.plannerRestAPI.services.implemntations;

import com.example.plannerRestAPI.dtos.AuthorityDTO;
import com.example.plannerRestAPI.dtos.UserAuthorityDTO;
import com.example.plannerRestAPI.entities.Authority;
import com.example.plannerRestAPI.entities.User;
import com.example.plannerRestAPI.exceptions.ApiRequestException;
import com.example.plannerRestAPI.repositories.AuthorityRepository;
import com.example.plannerRestAPI.services.AuthorityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public List<AuthorityDTO> findAll() {

        List<AuthorityDTO> authorityDTOS = authorityRepository.findAll().stream().map(authority -> new AuthorityDTO(authority.getId(), authority.getName())).collect(Collectors.toList());

        return authorityDTOS;
    }

    @Override
    public Optional<AuthorityDTO> findAuthority(int id) {

        Optional<AuthorityDTO> authority = authorityRepository.findById(id).map(auth -> new AuthorityDTO(auth.getId(), auth.getName()));

        return authority;
    }

    @Override
    public AuthorityDTO addNewAuthority(AuthorityDTO authorityDTO) throws ApiRequestException {

        AuthorityDTO authorityInserted = new AuthorityDTO();
        Authority authority = new Authority();
        authority.setName(authorityDTO.getName());

        try{

            Authority saveAuthority = authorityRepository.save(authority);
            authorityInserted.setId(saveAuthority.getId());
            authorityInserted.setName(saveAuthority.getName());

        } catch (Exception exception) {
            throw new ApiRequestException("Authority not inserted!");
        }

        return authorityInserted;
    }

    @Override
    public AuthorityDTO updateAuthority(AuthorityDTO authorityDTO) throws ApiRequestException {

        Authority authority = new Authority();
        AuthorityDTO authorityInserted = new AuthorityDTO();

        if(authorityDTO.getId() > 0) {
            authority.setId(authorityDTO.getId());
            authority.setName(authorityDTO.getName());

            try {
                authorityRepository.save(authority);
            } catch (Exception exception) {
                throw new ApiRequestException("Authority not updated!");
            }

        } else {
            throw new ApiRequestException("Authority not updated! Bad format!");
        }

        authorityInserted.setId(authority.getId());
        authorityInserted.setName(authority.getName());

        return authorityInserted;
    }


    @Override
    public void deleteAuthority(int id) throws ApiRequestException {

        try {
            authorityRepository.deleteById(id);
        } catch (Exception exception) {
            throw new ApiRequestException("Authority not deleted!");
        }
    }

    @Override
    public Authority findAuthoritiesById(Integer id) {

        return authorityRepository.findAllById(id);
    }

    @Override
    public List<UserAuthorityDTO> getAllUsersForAuthority(Integer id) throws ApiRequestException {

        List<UserAuthorityDTO> authorityDTOS = new ArrayList<>();

        try {
            Authority authority = findAuthoritiesById(id);

            authority.getUsers().forEach(user ->  {

                UserAuthorityDTO userAuthorityDTO = new UserAuthorityDTO();
                userAuthorityDTO.setId(user.getId());
                userAuthorityDTO.setUsername(user.getUsername());

                authorityDTOS.add(userAuthorityDTO);

            });

        } catch (Exception exception) {
            throw new ApiRequestException("There was a problem trying to get all users for this authority. This is the error " + exception.getMessage());
        }

        return authorityDTOS;
    }

    @Override
    public List<UserAuthorityDTO> updateAuthorityForUser(Integer id) throws ApiRequestException {

        List<User> users = new ArrayList<>();
        List<UserAuthorityDTO> userAuthorityList = new ArrayList<>();

        try {
            Authority authority = findAuthoritiesById(id);
            authority.getUsers().stream().forEach(user -> users.add(user));
            authority.setUsers(users);
            authorityRepository.save(authority);

            users.stream().forEach(user -> {

                UserAuthorityDTO userAuthorityDTO = new UserAuthorityDTO();
                userAuthorityDTO.setId(user.getId());
                userAuthorityDTO.setUsername(user.getUsername());

                userAuthorityList.add(userAuthorityDTO);

            });

        } catch (Exception exception) {
            throw new ApiRequestException("There was a error trying to update the authority for users! This is the error " + exception.getMessage());
        }

        return userAuthorityList;

    }

    @Override
    public void removeAuthorityForAllUsers(Integer id) throws ApiRequestException {

        Authority authority = new Authority();
        Optional<AuthorityDTO> authorityDTO = findAuthority(id);
        authorityDTO.orElseThrow( () -> new ApiRequestException("No authority found!") );

        authority.setId(authorityDTO.get().getId());
        authority.setName(authorityDTO.get().getName());

        try {
            authorityRepository.save(authority);
        } catch (Exception exception) {
            throw new ApiRequestException("There was a problem trying to remove the authority for all users. This is the error " + exception.getMessage());
        }
    }

    @Override
    public void addAuthorityForAllUsers() {

    }
}
