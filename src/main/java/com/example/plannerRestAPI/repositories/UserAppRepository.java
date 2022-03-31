package com.example.plannerRestAPI.repositories;

import com.example.plannerRestAPI.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAppRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    @Modifying
    @Query(value = """
                DELETE `users`
                    ,`user_authority`
                FROM `users`
                LEFT JOIN `user_authority` ON `users`.`id` = `user_authority`.`id_user`
                WHERE `users`.`id` = :id
                """, nativeQuery = true)
    void deleteUser(@Param(value = "id") int id);

}
