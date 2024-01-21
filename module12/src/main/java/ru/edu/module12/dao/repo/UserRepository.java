package ru.edu.module12.dao.repo;

import ru.edu.module12.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

        List<UserEntity> findAllByNameOrderByAgeAsc(String name);
}
