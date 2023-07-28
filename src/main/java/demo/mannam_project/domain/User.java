package demo.mannam_project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data // @Getter. @Setter, @ToString, @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // 회원 일련번호

    @Column(nullable = false, length = 50, unique = true)
    private String username; // 아이디

    @Column(length = 100)
    private String password;

    @Column(nullable = false, length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @CreationTimestamp // 현재 시간이 기본값으로 등록되도록 설정
    private Timestamp createDate;
}
