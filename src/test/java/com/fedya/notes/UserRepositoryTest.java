package com.fedya.notes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;

    @Test
    public void testCreateUser(){
        User user = new User();
        user.setName("Alex2000");
        user.setPassword("password");
        User savedUser = repo.save(user);
        User existingUser = entityManager.find(User.class, savedUser.getId());
        assertEquals(user.getName(), existingUser.getName());
    }
    @Test
    public void testCreateUser2(){
        User user = new User();
        user.setName("Alex1999");
        user.setPassword("password");
        User savedUser = repo.save(user);
        User existingUser = entityManager.find(User.class, savedUser.getId());
        assertEquals(user.getName(), existingUser.getName());
    }
}