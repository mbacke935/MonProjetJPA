package en.edu.ucak.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.persistence.annotations.Cache;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Carte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime dateCreation;

    @OneToOne
    @JoinColumn(nullable = false)
    private Client proprietaire;

}
