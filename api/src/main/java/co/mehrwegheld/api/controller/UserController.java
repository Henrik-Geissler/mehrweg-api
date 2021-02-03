package co.mehrwegheld.api.controller;

import co.mehrwegheld.api.error.HttpCodeHandler;
import co.mehrwegheld.api.model.db.User;
import co.mehrwegheld.api.repository.UserRepository;
import co.mehrwegheld.api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController implements HttpCodeHandler {

    private final UserRepository userRepository;
    private final UserService userService;


    @PostMapping("/create")
    public ResponseEntity<Object> addUser(@RequestParam("name") String name,
                                          @RequestParam("username") String username,
                                          @RequestParam("password") String password,
                                          @RequestParam("email") String email) {

        Optional<User> userOptional = userService.createUser(name, username, password, email);
        if (!userOptional.isPresent()) {
            log.error("The user could not be created - input was: name: {}, username: {}, password: {}, email: {}",
                    name, username, password, email);

            return handleError("User could not be created!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        userRepository.save(userOptional.get());
        return handleSuccess();
    }
}
