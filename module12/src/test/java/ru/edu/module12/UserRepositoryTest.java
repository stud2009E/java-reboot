package ru.edu.module12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import ru.edu.module12.dao.entity.UserEntity;
import ru.edu.module12.dao.repo.UserRepository;

import java.util.List;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
@Sql(scripts = "classpath:data-h2.sql")
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    void findAllByNameOrderByAgeAscTest(){

        List<UserEntity> users = repository.findAllByNameOrderByAgeAsc("Ivan");

        Assertions.assertEquals(users.size(), 2);
    }

}
