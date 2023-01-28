package com.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob //대용량 데이터
    private String content;

    @ColumnDefault("0")
    private int count; //조회수

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user; //DB는 오브젝트를 저장할 수 없지만, JPA의 맵핑을 사용하면 할 수 있다.

    //fetch : Lazy는 필요할때만 가져와라. Eager는 조회시 무조건 들고오는 것이다.
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // 연관관계의 주인이 아니다. Reply테이블의 reply가 주인이다.(FK가 아님) Jointable을 통한 외래키 맵핑이 필요없다.
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;
}
