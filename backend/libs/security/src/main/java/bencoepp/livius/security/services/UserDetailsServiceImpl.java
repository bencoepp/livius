package bencoepp.livius.security.services;

import bencoepp.livius.entities.user.User;
import bencoepp.livius.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The UserDetailsServiceImpl class is an implementation of the UserDetailsService interface.
 * It is responsible for loading the user details from the database based on the given username.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    /**
     * Loads the user details based on the given username.
     *
     * @param username The username of the user to load.
     * @throws UsernameNotFoundException If the user is not found in the database.
     * @return An instance of UserDetails containing the user details.
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

}
