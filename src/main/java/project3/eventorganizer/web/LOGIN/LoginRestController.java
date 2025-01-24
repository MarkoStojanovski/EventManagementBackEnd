package project3.eventorganizer.web.LOGIN;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project3.eventorganizer.models.DTO.LoginDTO;
import project3.eventorganizer.models.User;
import project3.eventorganizer.service.AuthService;
import project3.eventorganizer.service.UserService;

@RestController
@RequestMapping("/api")
public class LoginRestController {

    private final AuthService authService;
    private final UserService userService;

    public LoginRestController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(
            @RequestBody LoginDTO loginDTO
            ) {
        User user = this.authService.login(
                loginDTO.getEmail(),
                loginDTO.getPassword()
        );
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
