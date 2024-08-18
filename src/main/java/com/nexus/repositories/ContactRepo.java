package com.nexus.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nexus.entities.Contact;
import com.nexus.entities.User;

@Repository
public interface ContactRepo extends JpaRepository<Contact, String> {

    // Custom finder method to find contacts by user
    List<Contact> findByUser(User user);

    // Custom query method to find contacts by user ID
    @Query("SELECT c FROM Contact c WHERE c.user.id = :userId")
    List<Contact> findByUserId(@Param("userId") String userId);
}
