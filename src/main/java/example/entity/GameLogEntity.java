package example.entity;

import example.dto.GameLogDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "game_log")
public class GameLogEntity extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer log_no;

    @Column(columnDefinition = "int default 1")
    private Integer final_score;

    @Column(columnDefinition = "int default 1")
    private Integer final_round;

    @Column(columnDefinition = "int default 0")
    private Integer final_money;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private UserEntity userEntity;

    public GameLogDto toDto(){
        return GameLogDto.builder().nickname( userEntity.getNickname() )
                .log_no(log_no).final_score(final_score)
                .final_round(final_round).final_money(final_money)
                .createDate(getCreateDate().toString())
                .updateDate(getUpdateDate().toString())
                .build();
    }
}
