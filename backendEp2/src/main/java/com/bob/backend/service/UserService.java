package com.bob.backend.service;

import com.bob.backend.entity.User;
import com.bob.backend.exception.BaseException;
import com.bob.backend.exception.UserException;
import com.bob.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findById(String id) {
        return repository.findById(id);
    }
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    // if use this you need to really sure
    public User update(User user) {
        return repository.save(user);
    }

    // use this method is saver than upper method
    public User updateName(String id, String name) throws UserException {
        Optional<User> opt = repository.findById(id);
        if (opt.isEmpty()) {
           throw UserException.notFound();
        }

        User user = opt.get();
        user.setName(name);

        return  repository.save(user);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
    public User create(String email, String password, String name) throws BaseException {

        // validate
        if (Objects.isNull(email)) {

            // throw error email null
            throw UserException.createEmailNull();
        }

        if (Objects.isNull(password)) {

            // throw error password null
            throw UserException.createPasswordNull();
        }

        if (Objects.isNull(name)) {
            // throw error name null
            throw UserException.createNameNull();
        }

        // verify
        if (repository.existsByEmail(email)) {
            throw UserException.createEmailDuplicated();
        }


        // save

        User entity = new User();
        entity.setEmail(email);
        entity.setPassword(passwordEncoder.encode(password));
        entity.setName(name);

        return repository.save(entity);
    }
}
