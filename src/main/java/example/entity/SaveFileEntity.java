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
@Table(name = "save_file")
public class SaveFileEntity {

    @Id
    private Integer user_no; //기본키

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId // 외래키를 기본키로 매핑해주는 핵심 어노테이션입니다.
    @JoinColumn(name = "user_no") // DB에 생성될 컬럼명
    private UserEntity userEntity;

    @Column(columnDefinition = "int default 1")
    private Integer current_round;

    @Column(columnDefinition = "int default 5")
    private Integer current_hp;

    @Column(columnDefinition = "int default 5")
    private Integer current_discard;

    @Column(length = 1000)
    private String current_grave;

    @Column(length = 50)
    private String current_hand;

    @Column(columnDefinition = "int default 0")
    private Integer current_money;

    @Column(columnDefinition = "int default 0")
    private Integer current_score;

    @Column(length = 1000)
    private String card;

    @Column(length = 500)
    private String item;
}
