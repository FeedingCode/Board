package kr.ac.kopo.board.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "board")

public class Reply extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // maria db여서 Identity , oracle = sequence
    private Long rno;

    private String text;

    private String replyer;

    @ManyToOne(fetch = FetchType.LAZY)//board 1:  reply 다
    private Board board; // board_bno(board 테이블의 pk)
}
