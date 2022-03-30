package com.example.plannerRestAPI.services.implemntations;

import com.example.plannerRestAPI.dtos.AuthorityDTO;
import com.example.plannerRestAPI.dtos.UserAuthorityDTO;
import com.example.plannerRestAPI.dtos.UserDTO;
import com.example.plannerRestAPI.entities.User;
import com.example.plannerRestAPI.exceptions.ApiRequestException;
import com.example.plannerRestAPI.repositories.UserAppRepository;
import com.example.plannerRestAPI.services.UserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserAppServiceImpl implements UserAppService {

    private final UserAppRepository userAppRepository;

    public UserAppServiceImpl(UserAppRepository userAppRepository) {
        this.userAppRepository = userAppRepository;

    }

    @Override
    public List<UserDTO> getAllUsers() {

        List<UserDTO> userDTOList = userAppRepository.findAll().stream().map(user -> new UserDTO(user.getId(), user.getUsername(), user.getEmail())).collect(Collectors.toList());

        return userDTOList;
    }

    @Override
    public UserDTO getUser(Integer id) throws ApiRequestException {

        return userAppRepository.findById(id).map(user -> new UserDTO(user.getId(), user.getUsername(), user.getEmail())).orElseThrow( () -> new ApiRequestException("No user found!"));
    }

    @Override
    public UserDTO addNewUser(UserDTO userDTO) throws ApiRequestException {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        boolean userAlreadyExistByUsername = findByUsername(userDTO.getUsername()).isPresent();

        if(userAlreadyExistByUsername) {
            throw new ApiRequestException("This username is already used!");
        }

        if(userDTO.getPassword().isEmpty()) {
            throw new ApiRequestException("Password should not by empty");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());

        try {
            userAppRepository.save(user);
        } catch (Exception exception) {
            throw new ApiRequestException("user not added! This is the error " + exception.getMessage());
        }

        userDTO.setId(user.getId());
        userDTO.setPassword(null);

        return userDTO;
    }

    @Override
    public UserDTO updateUser(Integer id, UserDTO userDTO) throws ApiRequestException {

        User user = userAppRepository.findById(id).orElseThrow(() -> new ApiRequestException("User not found!"));
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if(!userDTO.getPassword().isBlank())
                user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));


        if(!userDTO.getUsername().isBlank()) {
            if(!findByUsername(userDTO.getUsername()).isPresent())
                user.setUsername(userDTO.getUsername());
            else throw new ApiRequestException("Username already exist!");
        }

        user.setEmail(userDTO.getEmail());
        userDTO.setId(id);
        userDTO.setPassword(null);

        try {
            userAppRepository.save(user);
        } catch (Exception exception) {
            throw new ApiRequestException("User not updated. This is the error " + exception.getMessage() );
        }

        return userDTO;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userAppRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) throws ApiRequestException {

        userAppRepository.findById(id).orElseThrow(() -> new ApiRequestException("User not found!"));

        try {
            //userAppRepository.deleteById(id);
            userAppRepository.deleteUser(id);
        } catch (Exception exception) {
            throw new ApiRequestException(exception.getMessage());
        }
    }

    @Override
    public UserAuthorityDTO getAuthorityForUser(Integer id) throws ApiRequestException {

        User user = userAppRepository.findById(id).orElseThrow(() -> new ApiRequestException("User not found!"));
        List<AuthorityDTO> authorityDTOList = new ArrayList<>();
        UserAuthorityDTO userAuthorityDTO = new UserAuthorityDTO();
        userAuthorityDTO.setId(user.getId());

        user.getAuthorities().stream().forEach(authority -> {

            AuthorityDTO authorityDTO = new AuthorityDTO();
            authorityDTO.setId(authority.getId());
            authorityDTO.setName(authority.getName());

            authorityDTOList.add(authorityDTO);
        });

        userAuthorityDTO.setAuthorityList(authorityDTOList);

        return userAuthorityDTO;
    }
}
