package bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enchere {

    private LocalDateTime dateEnchere;
    private int montant_enchere;
    private Utilisateur utilisateur;
    private ArticleVendu articleVendu;
}
