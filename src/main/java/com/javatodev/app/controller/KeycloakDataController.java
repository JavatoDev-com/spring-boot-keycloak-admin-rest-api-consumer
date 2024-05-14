package com.javatodev.app.controller;

import com.javatodev.app.config.KeycloakManager;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/keycloak-data")
@RequiredArgsConstructor
public class KeycloakDataController {

    private final KeycloakManager keycloakManager;

    @RequestMapping("/get-users")
    public ResponseEntity<List<UserRepresentation>> readKeycloakUsers() {
        try {
            Keycloak keycloak = keycloakManager.getInstance();

            List<UserRepresentation> list = keycloakManager.getKeyCloakInstanceWithRealm().users().list();
            log.info("Users {}", list);
        } catch (Exception e) {
            log.error("Error in reading groups {}", e.getMessage());
        }
        return ResponseEntity.ok(null);
    }

}
