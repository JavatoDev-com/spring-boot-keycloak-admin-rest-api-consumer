package com.javatodev.app.service;

import com.javatodev.app.config.KeycloakManager;

import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.UUID;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakDataService {

    private final KeycloakManager keycloakManager;

    @PostConstruct
    public void createUser() {
        RealmResource keyCloakInstanceWithRealm = keycloakManager.getKeyCloakInstanceWithRealm();
        UserRepresentation representation = new UserRepresentation();

        String email = userRegistration(representation, keyCloakInstanceWithRealm);
        searchUsers(keyCloakInstanceWithRealm);
        searchUserByEmail(email, keyCloakInstanceWithRealm);
    }

    private void searchUserByEmail(String email, RealmResource keyCloakInstanceWithRealm) {
        log.info("Searching user by email: {}", email);
        keyCloakInstanceWithRealm.users().searchByEmail(email, true)
            .forEach(user -> log.info("Searched User: {}", user.getUsername()));
    }

    private static String userRegistration(UserRepresentation representation, RealmResource keyCloakInstanceWithRealm) {
        String uniqueUsername = UUID.randomUUID().toString();
        representation.setUsername(uniqueUsername);
        representation.setEmail(uniqueUsername + "@test.com");
        representation.setEmailVerified(Boolean.TRUE);
        representation.setEnabled(Boolean.TRUE);
        log.info("Creating user with username: {}", representation);
        keyCloakInstanceWithRealm.users().create(representation);
        return uniqueUsername + "@test.com";
    }

    private void searchUsers(RealmResource keyCloakInstanceWithRealm) {
        log.info("Searching users");
        keyCloakInstanceWithRealm.users().list().forEach(user ->
            log.info("User: {}", user.getUsername()));
    }
}
