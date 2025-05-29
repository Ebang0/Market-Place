package com.marketplace.Gateway.filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;


public class RouteValidator {
    public static final List<String> openApiEndpoints = List.of(
        "/api/auth_util/inscription",
        "/api/auth_util/connexion",
        "/api/auth_util/profile/{id}",
        "/api/auth_util/token/**"
    );

    public Predicate<ServerHttpRequest> isSecured = 
        request -> openApiEndpoints.stream()
                                    .noneMatch(uri ->
                                     request.getURI().getPath().contains(uri));
}
