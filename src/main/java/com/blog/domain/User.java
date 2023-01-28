package com.blog.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String username;
    @Column(nullable = false, length = 100)
    private String password;
    @Column(nullable = false, length = 50)
    private String email;

    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    //fetch : Lazy는 필요할때만 가져와라. Eager는 조회시 무조건 들고오는 것이다.
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER) // 연관관계의 주인이 아니다. Reply테이블의 reply가 주인이다.(FK가 아님) Jointable을 통한 외래키 맵핑이 필요없다.
    private List<Reply> reply;

    @CreationTimestamp //시간자동입력
    private Timestamp createDate;

}
