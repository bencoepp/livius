package bencoepp.livius.security.services;

import bencoepp.livius.entities.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * UserDetailsImpl is a class that implements the UserDetails interface.
 * It represents the user details retrieved from the database and contains
 * information such as the user's id, username, email, password, and authorities.
 */
@Getter
@Setter
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String id;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    /**
     * Creates a UserDetailsImpl object with the provided information.
     *
     * Constructs a UserDetailsImpl object using the provided id, username, email, password, and authorities.
     *
     * @param id           The unique identifier for the user.
     * @param username     The username of the user.
     * @param email        The email address of the user.
     * @param password     The password of the user.
     * @param authorities  The collection of authorities granted to the user.
     */
    public UserDetailsImpl(String id, String username, String email, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    /**
     * Builds a UserDetailsImpl object based on the provided User object.
     *
     * Constructs a UserDetailsImpl object using the user's id, username, email, password, and roles.
     * The user's roles are converted into a list of GrantedAuthority objects.
     *
     * @param user The User object from which to build the UserDetailsImpl object.
     * @return The constructed UserDetailsImpl object.
     */
    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Compares this UserDetailsImpl object with the specified object for equality.
     *
     * The comparison is based on the id property of the UserDetailsImpl object.
     *
     * Returns true if the specified object is also a UserDetailsImpl object,
     * and their id properties are equal. Otherwise, returns false.
     *
     * @param o the object to be compared for equality
     * @return true if the specified object is equal to this UserDetailsImpl object,
     *         false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
