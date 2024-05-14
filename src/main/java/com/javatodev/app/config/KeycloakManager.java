package com.javatodev.app.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class KeycloakManager {

    private static Keycloak keycloakInstance = null;

    private final KeycloakProperties keycloakProperties;

    public Keycloak getInstance() {

        if (keycloakInstance == null) {
            keycloakInstance = KeycloakBuilder
                .builder()
                .serverUrl(keycloakProperties.getUrl())
                .realm(keycloakProperties.getRealm())
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(keycloakProperties.getClientId())
                .clientSecret(keycloakProperties.getClientSecret())
                .build();
        }
        return keycloakInstance;
    }

    public RealmResource getKeyCloakInstanceWithRealm() {
        return getInstance().realm(keycloakProperties.getRealm());
    }

}
