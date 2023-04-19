package tech.getarrays.EntrepriseMicroservice.registration.token;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.getarrays.EntrepriseMicroservice.model.Entreprise;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "ConfirmationToken_entreprise")
public class ConfirmationToken {

    @SequenceGenerator(
            name = "confirmation_token_sequence_entreprise",
            sequenceName = "confirmation_token_sequence_entreprise",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "confirmation_token_sequence_entreprise"
    )
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "entreprise_id"
    )
    private Entreprise entreprise;

    public ConfirmationToken(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expiresAt,
                             Entreprise entreprise) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.entreprise = entreprise;
    }
}
