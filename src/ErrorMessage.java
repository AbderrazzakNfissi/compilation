import java.util.HashMap;
import java.util.Map;

public class ErrorMessage {
    private Map<ERR_SYNTAX, String> explicationMessage;

    public ErrorMessage(){
        explicationMessage = new HashMap<>();

        explicationMessage.put(ERR_SYNTAX.PROGRAM_ERR, "{Erreur syntaxique} : Erreur de declaration externe");
        explicationMessage.put(ERR_SYNTAX.PROGRAM2_ERR, "{Erreur syntaxique} : Erreur de declaration externe");
        explicationMessage.put(ERR_SYNTAX.EXTERNAL_DECLARATION_ERR, "{Erreur syntaxique} : Erreur de declaration externe");
        //explicationMessage.put(ERR_SYNTAX, "{Erreur syntaxique} : Erreur de declaration externe");

    }


}
