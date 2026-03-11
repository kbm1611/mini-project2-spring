package example.entity;

import example.dto.GameLogDto;
import example.dto.UserDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private SaveFileEntity saveFile;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<GameLogEntity> gameLogs = new ArrayList<>();

    public UserDto toDto(){
        return UserDto.builder()
                .user_no(user_no).uid(uid).upwd(upwd).nickname(nickname)
                .createDate(getCreateDate().toString())
                .updateDate(getUpdateDate().toString())
                .build();
    }
}
