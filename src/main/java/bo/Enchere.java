package bo;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalTime;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Enchere {
    private LocalTime dateEnchere;
    private int montant_enchere;
    private Utilisateur utilisateur;
    private ArticleVendu articleVendu;
}
