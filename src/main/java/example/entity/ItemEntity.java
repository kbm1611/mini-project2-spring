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
@Table(name = "item_info")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer item_no;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 10, nullable = false)
    private String type;

    @Column(nullable = false)
    private Integer price;

    @Column(length = 100)
    private String description;

    @Column(length = 20)
    private String effect_code;
}
