package kr.ac.kopo.board.service;

import jakarta.transaction.Transactional;
import kr.ac.kopo.board.dto.BoardDTO;
import kr.ac.kopo.board.dto.PageRequestDTO;
import kr.ac.kopo.board.dto.PageResultDTO;
import kr.ac.kopo.board.entity.Board;
import kr.ac.kopo.board.entity.Member;
import kr.ac.kopo.board.repository.BoardRepository;
import kr.ac.kopo.board.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {
    private  final BoardRepository repository; // 자동주입 final
    private  final ReplyRepository replyRepository; // 자동주입 final

    @Override
    public Long register(BoardDTO dto){
        log.info(dto);
        Board board = dtoToEntity(dto);
        repository.save(board);
        return board.getBno();
    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], BoardDTO> fn = (en -> entityToDTO((Board)en[0], (Member)en[1],(Long)en[2]));
        Page<Object[]> result = repository.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("bno").descending()));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public BoardDTO get(Long bno) {
        Object result = repository.getBoardByBno(bno);
        Object[] arr = (Object[]) result;


        return entityToDTO((Board) arr[0], (Member) arr[1], (Long) arr[2]);
    }

    //댓글삭제와 원글 삭제를 같이 하기 위해 transactional 어노테이션 추가
    @Transactional
    @Override
    public void removeWithReplies(Long bno) {
        //댓글 삭제
        replyRepository.deleteByBno(bno);
        //원글삭제
        repository.deleteById(bno);
    }

    @Transactional
    @Override
    public void modify(BoardDTO boardDTO) {
        Board board = repository.getReferenceById(boardDTO.getBno());
        board.changeTitle(boardDTO.getTitle());
        board.changeContent(boardDTO.getContent());
        repository.save(board);
    }


}
