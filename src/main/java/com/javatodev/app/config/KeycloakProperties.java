package com.javatodev.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app.config.keycloak")
public class KeycloakProperties {
    private String realm;
    private String url;
    private String clientId;
    private String clientSecret;
}
