package com.example.module11.dao.service;

import com.example.module11.dao.entity.UserEntity;
import com.example.module11.dao.repo.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public void create(UserEntity user) {
        if (Objects.isNull(user.getAge()) || user.getAge() <= 0) {
            throw new IllegalArgumentException("Age < 0");
        }

        if (user.getName().isEmpty()) {
            throw new IllegalArgumentException("Empty name");
        }

        repository.save(user);
    }


    public void edit(UserEntity user) {
        if (Objects.isNull(user.getAge()) || user.getAge() <= 0) {
            throw new IllegalArgumentException("Age < 0");
        }

        if (user.getName().isEmpty()) {
            throw new IllegalArgumentException("Empty name");
        }

        findById(user.getId());

        repository.save(user);
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
