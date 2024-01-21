package ru.edu.module12;

import ru.edu.module12.dao.entity.UserEntity;
import ru.edu.module12.dao.repo.UserRepository;
import ru.edu.module12.dao.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository repository;

    private UserService service;

    @BeforeEach
    void setUp() {
        service = new UserService(repository);
    }

    @AfterEach
    void tearDown() {
        service = null;
    }

    @Test
    public void findAllTest() {
        List<UserEntity> users = new ArrayList<>(Arrays.asList(
                new UserEntity("Bob", 20),
                new UserEntity("Tom", 15)
        ));

        Mockito.when(repository.findAll()).thenReturn(users);
        Assertions.assertEquals(users, service.findAll());
    }


    @Test
    public void createUpdateNegativeTest(){
        UserEntity userEmpty = new UserEntity("", 0);
        UserEntity userWithoutAge = new UserEntity("WithName", 0);
        UserEntity userWithoutName = new UserEntity("", 10);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.create(userEmpty);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.create(userWithoutAge);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.create(userWithoutName);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.edit(userEmpty);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.edit(userWithoutAge);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.edit(userWithoutName);
        });
    }

    @Test
    public void createUpdatePositiveTest(){
        UserEntity user = new UserEntity("Test", 10);
        user.setId(1L);

        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(user));
        Mockito.when(repository.save(Mockito.any())).thenReturn(user);

        UserEntity savedUser = service.create(user);
        Assertions.assertEquals(savedUser.getName(), user.getName());
        Assertions.assertEquals(savedUser.getAge(), user.getAge());
        Mockito.verify(repository, Mockito.times(1)).save(user);


        UserEntity editUser = service.edit(user);
        Assertions.assertEquals(editUser.getName(), user.getName());
        Assertions.assertEquals(editUser.getAge(), user.getAge());
        Mockito.verify(repository, Mockito.times(1)).findById(user.getId());
        Mockito.verify(repository, Mockito.times(2)).save(user);
    }


    @Test
    public void deleteTest(){
        UserEntity user = new UserEntity("Test", 10);
        user.setId(1L);

        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(user));

        service.deleteById(user.getId());

        Mockito.verify(repository, Mockito.times(1)).deleteById(user.getId());
        Mockito.verify(repository, Mockito.times(1)).findById(user.getId());
    }

}
