package com.marketplace.Gateway.filter;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.marketplace.Gateway.jwService.JwtUtil;

import jakarta.ws.rs.core.HttpHeaders;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private RouteValidator routeValidator;
   
    @Autowired
    private JwtUtil jwtUtil;
    
    public AuthenticationFilter()
    {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (routeValidator.isSecured.test(exchange.getRequest())) {
                // Vérifie si le header Authorization est présent
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                
                // Vérifie le format du token
                if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid authorization header format");
                }

                String token = authHeader.substring(7);
                
                try {
                    // Valide le token sans besoin de UserDetails (vérifie seulement la signature et l'expiration)
                    jwtUtil.validateToken(token);
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized access to application", e);
                }
            }
            return chain.filter(exchange);
        };
    }

    public static class Config{
        
    }
}
