package example.entity;

import example.dto.GameLogDto;
import example.dto.UserDto;
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

    public UserDto toDto(){
        return UserDto.builder()
                .user_no(user_no)
                .uid(uid)
                .upwd(upwd)
                .nickname(uid)
                .updateDate(getUpdateDate().toString())
                .createDate(getCreateDate().toString()).build();
    }
}
