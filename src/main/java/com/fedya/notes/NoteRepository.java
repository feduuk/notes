package com.fedya.notes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
//    @Query("SELECT n FROM Note n WHERE n.user.id = ?1")
    @Query(value = "SELECT * FROM notes WHERE user_id = ?1"
            , nativeQuery = true)
    public List<Note> findNotesByUserId(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE notes SET text = ?2 WHERE id = ?1"
            , nativeQuery = true)
    int changeNote(Long id, String text);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM notes WHERE id = ?1"
            , nativeQuery = true)
    int deleteNote(Long id);
}
