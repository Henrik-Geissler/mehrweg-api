package co.mehrwegheld.api.service;

import co.mehrwegheld.api.model.db.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.Optional;

@Slf4j
@Service
public class UserService {
    public Optional<User> createUser(String name, String username, String password, String email) {
        if (isNullOrEmpty(name) || isNullOrEmpty(username) || isNullOrEmpty(password) || isNullOrEmpty(email)) {
            throw new InputMismatchException("Not all required values are present - check your input!");
        }
        return Optional.of(new User(name, username, password, email));
    }


    private boolean isNullOrEmpty(String string) {
        return string == null || string.equals("");
    }
}
