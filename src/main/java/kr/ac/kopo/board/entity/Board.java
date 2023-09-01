package kr.ac.kopo.board.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer") //writer 제외한 객체 정보에 대한 문자열 정보

public class Board extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;

    private String content;



//즉각로딩(Eager) 방식은 기본설정이며 무조건 모든 관계에 대해 Join을 하기 때문에
//불필요한 조인이 발생하게 됌
//데이터의 양이 많을시에는 즉각로딩 방식을 사용했을때 실행시간이 오래걸린다.
//지연로딩(Lazy) 방식 설정

    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer; //실제 board테이블에는 writer_email이라는 컬럼이 생성되고 fk(Member테이블의 email컬럼값만 참조하기위해)가 설정된다.



}
