package bencoepp.livius.user.controller;

import bencoepp.livius.entities.user.Role;
import bencoepp.livius.entities.user.User;
import bencoepp.livius.repositories.user.RoleRepository;
import bencoepp.livius.repositories.user.UserRepository;
import bencoepp.livius.security.payload.SignupRequest;
import bencoepp.livius.user.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Rest controller for managing users.
 * This class handles HTTP requests related to user operations.
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Retrieves the current authenticated user's information.
     *
     * @param principal The principal object containing the current user's authentication information.
     * @return ResponseEntity containing the User object or an appropriate HTTP status.
     */
    @GetMapping("/me")
    public ResponseEntity<User> getMe(Principal principal) {
        Optional<User> user = userRepository.findByUsername(principal.getName());
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Updates the information of the authenticated user.
     *
     * @param principal The principal object containing the current user's authentication information.
     * @param signupRequest The SignupRequest object containing the updated user information.
     * @return ResponseEntity<User> containing the updated User object or an appropriate HTTP status.
     */
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(Principal principal, @RequestBody SignupRequest signupRequest){
        Optional<User> user = userRepository.findByUsername(principal.getName());
        if(user.isPresent()){
            user.get().setEmail(signupRequest.getEmail());
            user.get().setPassword(encoder.encode(signupRequest.getPassword()));
            return ResponseEntity.ok(userRepository.save(user.get()));
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Initializes the roles in the system by reading the role data from a JSON file and saving them to the roleRepository.
     *
     * @throws JsonProcessingException If there is an error processing the JSON file.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void initializeRoles() throws JsonProcessingException {
        String input = getResourceFileAsString("data.json");
        ObjectMapper mapper = new ObjectMapper();
        List<Role> roles = mapper.readValue(input, new TypeReference<>() {
        });
        roleRepository.deleteAll();
        roleRepository.saveAll(roles);
    }

    /**
     * This method retrieves the contents of a resource file as a string.
     *
     * @param fileName The name of the resource file
     * @return The contents of the resource file as a string
     * @throws RuntimeException If the resource file is not found
     */
    public static String getResourceFileAsString(String fileName) {
        InputStream is = getResourceFileAsInputStream(fileName);
        if (is != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } else {
            throw new RuntimeException("resource not found");
        }
    }

    /**
     * Retrieves the resource file as an input stream.
     *
     * @param fileName The name of the resource file
     * @return The input stream of the resource file
     */
    public static InputStream getResourceFileAsInputStream(String fileName) {
        ClassLoader classLoader = UserService.class.getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }
}
