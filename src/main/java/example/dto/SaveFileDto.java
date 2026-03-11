package example.dto;

import example.entity.SaveFileEntity;
import example.entity.UserEntity;
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
    private String card;
    private String item;
    private String current_grave;
    private String current_hand;
    private String createDate;
    private String updateDate;

    public SaveFileEntity toEntity(){
        return SaveFileEntity.builder()
                .userEntity(UserEntity.builder().user_no(this.user_no).build())
                .current_round(this.current_round)
                .current_hp(this.current_hp)
                .current_discard(this.current_discard)
                .current_money(this.current_money)
                .current_score(this.current_score)
                .card(this.card)
                .item(this.item)
                .current_grave(this.current_grave)
                .current_hand(this.current_hand)
                .build();
    }

}
