package kr.ac.kopo.board.entity;

//날짜, 시간 등을 상속받는 클래스

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
abstract public class BaseEntity {
    //시간 생성
    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;

    //수정
    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;


}
