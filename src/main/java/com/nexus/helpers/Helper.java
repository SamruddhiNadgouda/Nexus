package com.nexus.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {

    public static String getEmailOfLoggedInUser(Authentication authentication) {
        String username = "";

        if (authentication.getPrincipal() instanceof OAuth2AuthenticatedPrincipal principal) {
            var oauth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            var clientId = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();
            var oauth2User = (OAuth2User) authentication.getPrincipal();

            if (clientId.equalsIgnoreCase("google")) {
                System.out.println("Getting email from Google");
                username = oauth2User.getAttribute("email").toString();
            } else if (clientId.equalsIgnoreCase("github")) {
                System.out.println("Getting email from GitHub");
                username = oauth2User.getAttribute("email") != null
                        ? oauth2User.getAttribute("email").toString()
                        : oauth2User.getAttribute("login").toString() + "@gmail.com";
            }
        } else {
            System.out.println("Getting data from local database");
            username = authentication.getName();
        }

        return username;
    }
}
