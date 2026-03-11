package example.dto;

import example.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor@NoArgsConstructor@Data@Builder

public class UserDto {
    private Integer user_no;
    private String uid;
    private String upwd;
    private String nickname;
    private String updateDate;
    private String createDate;

    public UserEntity toEntity(){
        return UserEntity.builder()
                .user_no(this.user_no)
                .uid(this.uid)
                .upwd(this.upwd)
                .nickname(this.nickname)
                .build();
    }



}
