package project3.eventorganizer.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project3.eventorganizer.models.User;
import project3.eventorganizer.models.exceptions.InvalidUserCredentials;
import project3.eventorganizer.models.exceptions.UserNotFoundException;
import project3.eventorganizer.repository.UserRepository;
import project3.eventorganizer.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User login(String email, String password) {
        User user = this.userRepository.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException();
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidUserCredentials();
        }

        return user;
    }

}
