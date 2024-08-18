package com.nexus.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.nexus.entities.Providers;
import com.nexus.entities.User;
import com.nexus.helpers.AppConstants;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(OAuthenticationSuccessHandler.class);

    @Autowired
    private UserRepo userRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        logger.info("OAuthAuthenticationSuccessHandler");

        // Identify the provider
        OAuth2AuthenticationToken oauth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        String authorizedClientRegistrationId = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();
        logger.info("Authorized Client Registration Id: " + authorizedClientRegistrationId);

        // Get the user details from the authentication object
        DefaultOAuth2User oauthUser = (DefaultOAuth2User) authentication.getPrincipal();
        oauthUser.getAttributes().forEach((key, value) -> {
            logger.info(key + ": " + value);
        });

        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        user.setEmailVerified(true);
        user.setEnabled(true);
        user.setPassword("dummy");
        if (authorizedClientRegistrationId.equalsIgnoreCase("google")) {
            user.setEmail(oauthUser.getAttribute("email").toString());
            user.setProfilePic(oauthUser.getAttribute("picture").toString());
            user.setName(oauthUser.getAttribute("name").toString());
            user.setProviderUserId(oauthUser.getName());
            user.setProvider(Providers.GOOGLE);
            user.setAbout("this account is created using google");
        } else if (authorizedClientRegistrationId.equalsIgnoreCase("github")) {
            String email = oauthUser.getAttribute("email") != null ? oauthUser.getAttribute("email").toString() : oauthUser.getAttribute("login").toString() + "@gmail.com";
            String picture = oauthUser.getAttribute("avatar_url").toString();
            String name = oauthUser.getAttribute("login").toString();
            user.setEmail(email);
            user.setProfilePic(picture);
            user.setName(name);
            user.setProviderUserId(oauthUser.getName());
            user.setProvider(Providers.GITHUB);
            user.setAbout("this account is created using github");
        } else if (authorizedClientRegistrationId.equalsIgnoreCase("linkedin")) {
            // Handle LinkedIn provider (not implemented in this code snippet)
            logger.info("LinkedIn provider is not yet implemented");
        } else {
            logger.warn("Unknown provider: " + authorizedClientRegistrationId);
            new DefaultRedirectStrategy().sendRedirect(request, response, "/login?error");
            return; // Exit the method if the provider is unknown
        }

        // Save the user if they don't already exist
        User user2 = userRepo.findByEmail(user.getEmail()).orElse(null);
        if (user2 == null) {
            userRepo.save(user);
            logger.info("New user saved: " + user.getEmail());
        }

        // Redirect to the user profile page after successful authentication
        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }
}
