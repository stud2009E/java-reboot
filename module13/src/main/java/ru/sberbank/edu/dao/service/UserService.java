package ru.sberbank.edu.dao.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.sberbank.edu.dao.entity.UserEntity;
import ru.sberbank.edu.dao.repo.UserRepository;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    public UserEntity create(UserEntity user) {
        if (Objects.isNull(user.getAge()) || user.getAge() <= 0) {
            throw new IllegalArgumentException("Age < 0");
        }

        if (user.getName().isEmpty()) {
            throw new IllegalArgumentException("Empty name");
        }

        return repository.save(user);
    }


    public UserEntity edit(UserEntity user) {
        if (Objects.isNull(user.getAge()) || user.getAge() <= 0) {
            throw new IllegalArgumentException("Age < 0");
        }

        if (user.getName().isEmpty()) {
            throw new IllegalArgumentException("Empty name");
        }

        findById(user.getId());

        return repository.save(user);
    }

    public UserEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public void deleteById(Long id) {
        findById(id);

        repository.deleteById(id);
    }


    public UserEntity getEmptyUserEntity(){
        return new UserEntity("", null);
    }
}
