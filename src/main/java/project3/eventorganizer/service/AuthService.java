package project3.eventorganizer.service;

import project3.eventorganizer.models.User;


public interface AuthService {

    User login(String email, String password);


}
