package ru.sberbank.edu.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.edu.dao.entity.UserEntity;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

        List<UserEntity> findAllByNameOrderByAgeAsc(String name);
}
