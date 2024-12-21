package newstart.entities;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="init_date")
    private LocalDateTime initDate;

    @Column(name="up_date")
    private LocalDateTime upDate;

    @PrePersist
    public void onPrePersist(){
        this.setUpDate(LocalDateTime.now());
        this.setInitDate(LocalDateTime.now());
    }

    @PreUpdate
    public void onPreUpdate(){
        this.setUpDate(LocalDateTime.now());
    }
}
