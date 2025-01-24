package project3.eventorganizer.web.LOGIN;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project3.eventorganizer.models.DTO.RegisterDTO;
import project3.eventorganizer.models.User;
import project3.eventorganizer.service.UserService;

@RestController
@RequestMapping("/api")
public class RegisterRestController {


    private final UserService userService;

    public RegisterRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(
            @RequestBody RegisterDTO registerDTO
            ){
        User user = this.userService.register(
                registerDTO.getEmail(),
                registerDTO.getPassword(),
                registerDTO.getRepeatPassword(),
                registerDTO.getName(),
                registerDTO.getSurname(),
                registerDTO.getRole()
        );
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
