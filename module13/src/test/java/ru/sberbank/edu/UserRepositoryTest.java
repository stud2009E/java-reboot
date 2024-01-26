package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import ru.sberbank.edu.dao.entity.UserEntity;
import ru.sberbank.edu.dao.repo.UserRepository;

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
