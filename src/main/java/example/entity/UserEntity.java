package example.entity;

import example.dto.GameLogDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Entity @Table(name = "users")
public class UserEntity extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_no;

    @Column(length = 30, nullable = false , unique = true)
    private String uid;

    @Column(length = 30, nullable = false)
    private String upwd;

    @Column(length = 20, nullable = false ,unique = true)
    private String nickname;


}
