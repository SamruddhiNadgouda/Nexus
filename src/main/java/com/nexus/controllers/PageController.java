package com.nexus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nexus.entities.User;
import com.nexus.forms.UserForm;
import com.nexus.helpers.Message;
import com.nexus.helpers.MessgeType;
import com.nexus.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home page handler");

        // sending data to view
        model.addAttribute("name", "Samruddhi Nadgouda");
        model.addAttribute("instagramID", "n_samu_29");
        model.addAttribute("githubRepo", "https://github.com/SamruddhiNadgouda/DSA-Java");
        return "home";
    }

    // about route
    @RequestMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("isLogin", true);
        System.out.println("About page loading");
        return "about";
    }

    // services route
    @RequestMapping("/services")
    public String servicesPage() {
        System.out.println("Services page loading");
        return "services";
    }

    // contact route
    @RequestMapping("/contact")
    public String contactPage() {
        System.out.println("Contact page loading");
        return "contact";
    }

    // login route
    @RequestMapping("/login")
    public String loginPage() {
        System.out.println("Login page loading");
        return "login";
    }

    // signup route
    @RequestMapping("/signup")
    public String signupPage(Model model) {

        UserForm userForm = new UserForm();
        // userForm.setName("Samruddhi");
        // userForm.setAbout("Hope you are doing great");
        model.addAttribute("userForm", userForm);

        System.out.println("Signup page loading");
        return "signup";
    }

    // processing signup
    @RequestMapping(value = "/do-signup", method = RequestMethod.POST)
    public String processSignUp(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult,
            HttpSession session) {
        System.out.println("Processing sign up registration");
        // fetch form data
        // UserForm
        System.out.println(userForm);
        // validate form data
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        // save to database
        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .build();

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());

        User savedUser = userService.saveUser(user);
        System.out.println("user saved");
        // message = "Registration successful"

        // add the message
        Message message = Message.builder().content("Registration Sucessful").type(MessgeType.green).build();
        session.setAttribute("message", message);
        // redirect to login page

        return "redirect:/signup";
    }
}
