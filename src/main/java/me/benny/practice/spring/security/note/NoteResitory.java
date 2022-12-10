package me.benny.practice.spring.security.note;

import me.benny.practice.spring.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteResitory extends JpaRepository<Note, Long> {
    List<Note> findByUserOrderByIdDesc(User user);

    Note findByIdAndUser(Long id,User user);
}
