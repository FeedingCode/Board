package kr.ac.kopo.board.repository;

import kr.ac.kopo.board.entity.Board;
import kr.ac.kopo.board.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Modifying //삭제
    @Query("delete from Reply r where r.board.bno = :bno")
    void deleteByBno(Long bno);
    //게시물로 댓글 목록 가져오기.
    List<Reply> getRepliesByBoardOrderByRno(Board board);


}
