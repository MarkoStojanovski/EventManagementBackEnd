package project3.eventorganizer.models.enumerations;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ADMIN, ORGANIZER, ATTENDEE;

    @Override
    public String getAuthority() {
        return name();
    }
}
