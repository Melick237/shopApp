package com.pomato.user.repositories;

import com.pomato.user.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {

    @Override
    List<User> findAll();

    Optional<User> findFirstByEmail(String email);
}