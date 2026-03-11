package example.entity;

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
public class GameLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer log_no;

    @Column(columnDefinition = "default 1")
    private Integer final_score;

    @Column(columnDefinition = "default 1")
    private Integer final_round;

    @Column(columnDefinition = "default 0")
    private Integer final_money;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private UserEntity userEntity;

}
