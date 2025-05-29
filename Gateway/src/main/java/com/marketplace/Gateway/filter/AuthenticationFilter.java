package com.marketplace.Gateway.filter;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

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
    public GatewayFilter apply(Config config)
    {
        return (exchange,chain)->{
            if(routeValidator.isSecured.test(exchange.getRequest()))
            {
                if(exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
                {
                    throw new RuntimeException("missing authorizationheader");
                }

                String authHeaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

                if(authHeaders!=null && authHeaders.startsWith("Bearer"))
                {
                    authHeaders=authHeaders.substring(7);
                }

                try {
                    
                   // template.getForObject("http://AUTH-GESTION-UTILISATEUR//api/auth_util/token/{id}?token"+authHeaders, String.class);
                    jwtUtil.validateToken(authHeaders, null);
                } catch (Exception e) {
                    throw new RuntimeException("un authorized access to application");
                }
            }
            return chain.filter(exchange);
        };
    }

    public static class Config{
        
    }
}
