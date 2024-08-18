package com.nexus.services;

import java.util.List;

import com.nexus.entities.Contact;

public interface ContactService {
    // Save contact
    Contact save(Contact contact);

    // Update contact
    Contact update(Contact contact);

    // Get all contacts
    List<Contact> getAll();

    // Get contact by ID
    Contact getById(String id);

    // Delete contact
    void delete(String id);

    // Search contacts
    List<Contact> search(String name, String email, String phoneNumber);

    // Get contacts by user ID
    List<Contact> getByUserId(String userId);
}
