package springbootforms.springbootforms.configuration;

import org.springframework.context.event.EventListener;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.session.HttpSessionCreatedEvent;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletContext;

@Component
public class SessionEventListener {
    private static final String ATTRIBUTE_NAME = "activeSessions";

    @EventListener
    public void handleSessionCreated(HttpSessionCreatedEvent event) {
        ServletContext ctx = event.getSession().getServletContext();
        Integer activeSessions = (Integer) ctx.getAttribute(ATTRIBUTE_NAME);
        if (activeSessions == null) {
            activeSessions = 0;
        }
        activeSessions++;
        ctx.setAttribute(ATTRIBUTE_NAME, activeSessions);
        System.out.println("Session Created: " + event.getSession().getId());
        UserDetails currUser = getCurrentUserDetails();
        System.out.println("For user: " + (currUser != null ? currUser.toString() : "No user"));
        System.out.println("Total Active Sessions: " + activeSessions);
    }

    @EventListener
    public void handleSessionDestroyed(HttpSessionDestroyedEvent event) {
        ServletContext ctx = event.getSession().getServletContext();
        Integer activeSessions = (Integer) ctx.getAttribute(ATTRIBUTE_NAME);
        if (activeSessions == null) {
            activeSessions = 0;
        } else if (activeSessions > 0) {
            activeSessions--;
        }
        ctx.setAttribute(ATTRIBUTE_NAME, activeSessions);
        System.out.println("Session Destroyed: " + event.getSession().getId());
        UserDetails currUser = getCurrentUserDetails();
        System.out.println("For user: " + (currUser != null ? currUser.toString() : "No user"));
        System.out.println("Total Active Sessions: " + activeSessions);
    }

    public UserDetails getCurrentUserDetails() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        return (UserDetails) authentication.getPrincipal();
    }
}
