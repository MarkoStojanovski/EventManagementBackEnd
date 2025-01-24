package project3.eventorganizer.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import project3.eventorganizer.models.User;
import project3.eventorganizer.models.enumerations.Role;

import java.util.List;

public interface UserService extends UserDetailsService {

    User register(String email, String password, String repeatPassword,
                  String name, String surname, Role role);

    User updateUser(String email, Role role);

    List<User> findAll();

    User findById(String email);

    void deleteUser(String email);

}
