package me.benny.practice.spring.security.note;

import lombok.RequiredArgsConstructor;
import me.benny.practice.spring.security.user.User;
import me.benny.practice.spring.security.user.UserNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NoteService {

    private final NoteResitory noteResitory;

    /**
     * 노트 조회
     * 유저는 본인의 노트만 조회할 수 있다.
     * 어드민은 모든 노트를 조회할 수 있다.
     *
     * @Param user 노트를 찾을 유저
     * @return 유저가 조회할 수 있는 모든 노트 List
     */
    @Transactional(readOnly = true)
    public List<Note> findByUser(User user){
        if(user == null){
            throw new UserNotFoundException();
        }
        if(user.isAdmin()) {
           return noteResitory.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }
        return noteResitory.findByUserOrderByIdDesc(user);
    }

    /**
     * 노트 저장
     *
     * @Param user 노트 저장하는 유저
     * @Param title 제목
     * @Param content 내용
     * @return 저장된 노트
     */
    public Note saveNote(User user, String title, String content) {
        if(user == null) {
            throw new UserNotFoundException();
        }
        return noteResitory.save(new Note(title, content, user));
    }

    /**
     * 노트 삭제
     *
     * @Param user 삭제하려는 노트의 유저
     * @Param noteId 노트 ID
     */
    public void deleteNote(User user, Long noteId) {
        if(user == null) {
            throw new UserNotFoundException();
        }
        Note note = noteResitory.findByIdAndUser(noteId,user);
        if(note != null ){
            noteResitory.delete(note);
        }
    }
}
