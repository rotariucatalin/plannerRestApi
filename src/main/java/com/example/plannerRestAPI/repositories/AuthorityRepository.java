package com.example.plannerRestAPI.repositories;

import com.example.plannerRestAPI.entities.Authority;
import com.example.plannerRestAPI.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

    Authority findAllById(Integer id);
}
