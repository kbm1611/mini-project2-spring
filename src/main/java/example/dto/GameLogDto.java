package example.dto;

import example.entity.GameLogEntity;
import example.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GameLogDto {
    private Integer log_no;
    private Integer final_score;
    private Integer final_round;
    private Integer final_money;
    private String createDate;
    private String updateDate;
    private String nickname;
    private Integer user_no;

    public GameLogEntity toEntity(){
        return GameLogEntity.builder()
                .log_no(this.log_no)
                .final_score(this.final_score)
                .final_round(this.final_round)
                .final_money(this.final_money)
                .userEntity(UserEntity.builder().user_no(this.user_no).build())
                .build();
    }
}
