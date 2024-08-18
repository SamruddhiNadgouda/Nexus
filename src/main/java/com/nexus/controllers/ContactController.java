package com.nexus.controllers;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nexus.entities.Contact;
import com.nexus.entities.User;
import com.nexus.forms.ContactForm;
import com.nexus.helpers.Helper;
import com.nexus.helpers.Message;
import com.nexus.helpers.MessgeType;
import com.nexus.services.ContactService;
import com.nexus.services.UserService;
import com.nexus.services.imageService; // Corrected service name

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    private final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private imageService imageService; // Corrected service name

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    public String addContactView(Model model) {
        ContactForm contactForm = new ContactForm();
        contactForm.setFavorite(true);
        model.addAttribute("contactForm", contactForm);
        return "user/add_contact"; // Path to Thymeleaf template
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result, Authentication authentication, HttpSession session) {
        // Validate form
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> logger.info(error.toString()));
            session.setAttribute("message", Message.builder()
                .content("Please correct the following errors")
                .type(MessgeType.red)
                .build());
            return "user/add_contact";
        }

        // Extract username from Authentication object
        String username = Helper.getEmailOfLoggedInUser(authentication);

        // Retrieve User from UserService
        User user = userService.getUserByEmail(username);

        String filename = UUID.randomUUID().toString();
        String fileURL = imageService.uploadImage(contactForm.getContactImage(), filename);

        // Convert form to Contact entity
        Contact contact = new Contact();
        contact.setId(UUID.randomUUID().toString()); // Set a unique ID
        contact.setName(contactForm.getName());
        contact.setFavorite(contactForm.isFavorite());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setUser(user);
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setPicture(fileURL);
        contact.setCloudinaryImagePublicId(filename);

        // Save contact
        contactService.save(contact);

        session.setAttribute("message",
            Message.builder()
            .content("You have successfully added a new contact")
            .type(MessgeType.green)
            .build());

        return "redirect:/user/contacts/add";
    }
}
