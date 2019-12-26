package ru.lanit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.lanit.entity.User;
import ru.lanit.exception.AuthException;
import ru.lanit.exception.NoEntityException;
import ru.lanit.repository.UserRepository;
import ru.lanit.service.security.JwtToken;
import java.util.ArrayList;

@Component
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtToken jwtToken;

    public String authenticate(String login, String password) throws AuthException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        } catch (DisabledException | BadCredentialsException e) {
            throw new AuthException();
        }
        final UserDetails userDetails = loadUserByUsername(login);
        return jwtToken.generateToken(userDetails);
    }

    public User getUser(Integer id) throws NoEntityException {
        return repository.findById(id).orElseThrow(() -> new NoEntityException());
    }

    public User getUser(String username) throws NoEntityException {
        return repository.findByUsername(username).orElseThrow(() -> new NoEntityException());
    }

    public User saveUser(String login, String password) {
        User user = new User(login, password);
        repository.saveAndFlush(user);
        return user;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = getUser(username);
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    new ArrayList<>()
            );
        } catch (NoEntityException e) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public User registerIfNotExist(String login, String password) {
        User user;
        try {
            user = getUser(login);
        } catch (NoEntityException e) {
            user = saveUser(login, new BCryptPasswordEncoder().encode(password));
        }
        return user;
    }
}
