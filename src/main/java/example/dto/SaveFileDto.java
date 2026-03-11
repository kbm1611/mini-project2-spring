package example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SaveFileDto {
    private Integer user_no;
    private Integer current_round;
    private Integer current_hp;
    private Integer current_discard;
    private Integer current_money;
    private Integer current_score;
    private String cards;
    private String items;
    private String grave;
    private String hand;
    private String createDate;
    private String updateDate;

    public SaveFileEntity toEntity(){
        return SaveFileEntity.builder()
                .user_no(this.user_no)
                .current_round(this.current_round)
                .current_hp(this.current_hp)
                .current_discard(this.current_discard)
                .current_money(this.current_money)
                .current_score(this.current_score)
                .cards(this.cards)
                .items(this.items)
                .grave(this.grave)
                .hand(this.hand)
                .build();
    }

}
