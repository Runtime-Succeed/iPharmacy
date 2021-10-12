package com.example.iPharmacy;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@Path("/")
public class JerseyResource {
    @GET
    @Path("login")
    @Produces(MediaType.TEXT_HTML)
    public String login() {
        return "Log in with <a href=\"/oauth2/authorization/github\">GitHub</a>";
    }

    public String name = "";
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String home(@Context SecurityContext securityContext) {
        OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) securityContext.getUserPrincipal();
        OAuth2AuthenticatedPrincipal authenticatedPrincipal = authenticationToken.getPrincipal();
        String userName = authenticatedPrincipal.getAttribute("login");
        name = userName;
        return "Hello " + userName + ", welcome back to iPharmacy.";
    }

    public String getName() {
        return name;
    }
}
