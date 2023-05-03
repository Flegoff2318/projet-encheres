package bo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleVendu {
    private int noArticle;
    private String nomArticle;
    private String description;
    private LocalDate dateDebutEncheres;
    private LocalDate dateFinEncheres;
    private int miseAPrix;
    private int prixVente;
    private int etatVente;
    private Utilisateur utilisateur;
    private Categorie categorie;
}
