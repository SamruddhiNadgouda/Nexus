package com.nexus.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexus.entities.Contact;
import com.nexus.helpers.ResourceNotFoundException;
import com.nexus.repositories.ContactRepo;
import com.nexus.services.ContactService;

@Service
public class ContactServiceImpl implements ContactService {
    
    @Autowired
    private ContactRepo contactRepo;

    @Override
    public Contact save(Contact contact) {
        String contactId = UUID.randomUUID().toString();
        contact.setId(contactId);
        return contactRepo.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        // Implementation should be provided based on your use case
        // For now, we'll throw an exception
        throw new UnsupportedOperationException("Update method is not implemented.");
    }

    @Override
    public List<Contact> getAll() {
        return contactRepo.findAll();
    }

    @Override
    public Contact getById(String id) {
        return contactRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with given id: " + id));
    }

    @Override
    public void delete(String id) {
        Contact contact = contactRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with given id: " + id));
        contactRepo.delete(contact);
    }

    @Override
    public List<Contact> search(String name, String email, String phoneNumber) {
        // Implement search logic if needed
        throw new UnsupportedOperationException("Search method is not implemented.");
    }

    @Override
    public List<Contact> getByUserId(String userId) {
        return contactRepo.findByUserId(userId);
    }
}
