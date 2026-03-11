package example.dto;

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
    private String nickname;
    private String play_date;
    private Integer final_score;
    private Integer final_round;
    private Integer final_money;
    private String createDate;
    private String updateDate;

    public GameLogEntity toEntity(){
        return GameLogEntity.builder()
                .log_no(this.log_no)
                .nickname(this.nickname)
                .play_date(this.play_date)
                .final_score(this.final_score)
                .final_round(this.final_round)
                .final_money(this.final_money)
                .build();
    }
}
