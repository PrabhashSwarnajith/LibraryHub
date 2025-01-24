package com.libraryhub.library_backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.libraryhub.library_backend.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User findByUsername(String username);

}