package kr.ac.kopo.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


@Entity //테이블이 생성될때 필요한 Entity
@Builder
@AllArgsConstructor //생성자 자동생성
@NoArgsConstructor //기본생성자 자동생성
@Getter
@ToString


public class Member extends BaseEntity{

    @Id //pk 설정됨
    private String email;

    private String password;

    private String name;
}
