package com.fedya.notes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.Arrays;

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
    @Autowired
    private NoteRepository noteRepository;

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
        user.setEmail("myemail@email");
        user.setNotes(new ArrayList<Note>());
        Note note = new Note();
        note.setText("mytext alex");
        user.getNotes().add(note);
        User savedUser = repo.save(user);
        User existingUser = entityManager.find(User.class, savedUser.getId());
        assertEquals(user.getName(), existingUser.getName());
    }
    @Test
    public void testCreateUser3(){
        User user = new User();
        user.setName("Garry1999");
        user.setPassword("password");
        user.setEmail("garryemail@email");
        Note note = new Note();
        note.setText("mytext garry");
        user.setNotes(Arrays.asList(note));
        User savedUser = repo.save(user);
        User existingUser = entityManager.find(User.class, savedUser.getId());
        assertEquals(user.getName(), existingUser.getName());
    }
    @Test
    public void testCreateUser4(){
        User user = new User();
        user.setName("Garry1999");
        user.setPassword("password");
        user.setEmail("il@email");
        user.setId(26l);

        Note note = new Note();
        note.setText("Hogwartz");
        note.setUser(user);
        Note savedNote = noteRepository.save(note);
        Note existingNote = entityManager.find(Note.class, savedNote.getId());
        assertEquals(note.getText(), existingNote.getText());
    }
}