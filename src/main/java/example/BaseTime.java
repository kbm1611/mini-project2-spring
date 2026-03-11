package example;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass// 1]
@Getter // 2] 상속받은 앤티티가 맴버변수 사용
@EntityListeners(AuditingEntityListener.class)  // // 4] 해당 앤티티 자동 감사 적용
public class BaseTime {

   @CreatedDate  // 3] 앤티티 생성날짜/시간 주입
    private LocalDateTime createDate;
   @LastModifiedDate // 3] 앤티티 수정날짜/시간 주입
    private LocalDateTime updateDate;
}
