package bo;

import helpers.TokenGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Token {

    private static final int DAY = 1;

    private int noToken;
    private Utilisateur utilisateur;
    private String userToken;
    private String passwordToken;
    private LocalDate expirationDate;

    // Préférer utiliser le setUtilisateur et ne pas passer l'utilisateur en paramètre.
    public Token(){
        userToken = TokenGenerator.generateToken();
        passwordToken = TokenGenerator.generateToken();
        expirationDate = LocalDate.now().plusDays(DAY);
    }

    public Token(String userString,String passwordString){
        userToken=userString;
        passwordToken=passwordString;
    }

}
