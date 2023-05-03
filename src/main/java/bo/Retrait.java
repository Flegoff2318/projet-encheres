package bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Retrait {
    private ArticleVendu article;
    private String rue;
    private String code_postal;
    private String ville;
}
