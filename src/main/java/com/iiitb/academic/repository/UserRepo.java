package com.iiitb.academic.repository;

import com.iiitb.academic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String> {


    @Query("SELECT u FROM User u where u.username = ?1")
    public User findUserByUsername(String username);

}