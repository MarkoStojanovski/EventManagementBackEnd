package project3.eventorganizer.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project3.eventorganizer.models.User;
import project3.eventorganizer.models.enumerations.Role;
import project3.eventorganizer.models.exceptions.PasswordsNotMatchingException;
import project3.eventorganizer.models.exceptions.UserNotFoundException;
import project3.eventorganizer.models.exceptions.UsernameOrPasswordEmptyException;
import project3.eventorganizer.repository.UserRepository;
import project3.eventorganizer.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User register(String email, String password, String repeatPassword,
                         String name, String surname, Role role) {
        if(email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new UsernameOrPasswordEmptyException();
        }

        if(!password.equals(repeatPassword)){
            throw new PasswordsNotMatchingException();
        }
        User user = new User(
                email,
                passwordEncoder.encode(password),
                name,
                surname,
                role
        );
        user = this.userRepository.save(user);

        return user;
    }

    @Override
    @Transactional
    public User updateUser(String email, Role role) {
        User user = this.userRepository.findByEmail(email);
        if (user == null){
            throw new UserNotFoundException();
        }
        user.setRole(role);
        return this.userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User findById(String email) {
        return this.userRepository.findById(email)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void deleteUser(String email) {
        User user = this.userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException();
        }
        user.setDeleted(true);
        this.userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(username);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }
}
