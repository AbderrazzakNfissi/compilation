
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.PreferencesFactory;

public class AnalyseurSyntaxique implements SyntaxInterface{
   private   ArrayList<CODES_LEX> listLexical = new ArrayList<>();
   private ArrayList<String> errorName =  new ArrayList<>();
   private CODES_LEX CODE_LEX_Cour;
   private int index = 0;



    private String courant;
    AnalyseurSyntaxique() throws Exception{
     analyseurLexical();
    }
    void analyseurLexical() throws Exception{
        File file = new File("C:\\Users\\Dell\\Desktop\\code\\code.fc");
        //File file = new File("C:\\Users\\AyouByte\\Desktop\\projet compila\\last dial last\\FCODE-cloned\\fcode.fc");

        FileReader fr = new FileReader(file);
     BufferedReader bf = new BufferedReader(fr);
     List<Character> charList = new ArrayList<>();
     int c;
     String line = "";
     String code = "";

     while((line = bf.readLine()) != null) {
      code += line + " ";
     }
     for(int i=0;i<code.length();i++){
      charList.add(code.charAt(i));
     }
     AnalyseurLexical al = new AnalyseurLexical(charList);
     al.Lire_Car();
     this.listLexical = new ArrayList<>();
     this.errorName = new ArrayList<>();
     while (al.getCour()<charList.size()-1){
      TSym_Cour sym_cour = al.sym_suivant();
      if(sym_cour.CODE != CODES_LEX.COMMENT_TOKEN){
          errorName.add(sym_cour.nom);
          listLexical.add( sym_cour.CODE);
      }
      al.Lire_Car();
      while((al.Car_Cour==' ' || al.Car_Cour=='\n' || al.Car_Cour=='\t') && al.getCour()<charList.size()) {
       al.Lire_Car();
      }
     }
     fr.close();

    }


    public void display(){
     for (String s : errorName) {
      System.out.println(s);
     }
    }




    public CODES_LEX Sym_Suiv() throws  Exception{
     if(index<=listLexical.size() - 1) {
         courant = errorName.get(index);
         CODE_LEX_Cour = listLexical.get(index++);
     }
       return CODE_LEX_Cour;
    }

    void Test_Symbole(CODES_LEX cl,ERR_SYNTAX ERR) throws Exception{
      if(cl == CODE_LEX_Cour){
       Sym_Suiv();
      }else{
       ERROR(courant, ERR);
       return;
      }
    }
    public String getMessage(String courant,ERR_SYNTAX ERR){
        String messageErr = "";
        switch (ERR){
            case ID_ERR:
                messageErr+="expected identifier ==> found "+courant;
                break;
            case ENTIER_ERR:
                messageErr+="expected entier ==> found "+courant;
                break;
            case FONCTION_ERR:
                messageErr+="expected fonction ==> found "+courant;
                break;
            case DEBUT_ERR:
                messageErr+="expected debut ==> found "+courant;
                break;
            case TYPE_SPECIFIER_ERR:
                messageErr+="expected entier or car or reel or vide  ==> found "+courant;
                break;
            case FIN_ERR:
                messageErr+="expected fin  ==> found "+courant;
                break;
            case RETOURNER_ERR:
                messageErr+="expected retourner  ==> found "+courant;
                break;
            case REEL_ERR:
                messageErr+="expected reel  ==> found "+courant;
                break;
            case VIDE_ERR:
                messageErr+="expected vide  ==> found "+courant;
                break;
            case SI_ERR:
                messageErr+="expected si  ==> found "+courant;
                break;
            case SINON_ERR:
                messageErr+="expected sinon  ==> found "+courant;
                break;
            case ALORS_ERR:
                messageErr+="expected alors  ==> found "+courant;
                break;
            case FINSI_ERR:
                messageErr+="expected finsi  ==> found "+courant;
                break;
            case SELON_ERR:
                messageErr+="expected selon  ==> found "+courant;
                break;
            case FAIRE_ERR:
                messageErr+="expected faire  ==> found "+courant;
                break;
            case FINSELON_ERR:
                messageErr+="expected finselon  ==> found "+courant;
                break;
            case TANT_QUE_ERR:
                messageErr+="expected tantque  ==> found "+courant;
                break;
            case FINTQ_ERR:
                messageErr+="expected fintq  ==> found "+courant;
                break;
            case REPETER_ERR:
                messageErr+="expected repeter  ==> found "+courant;
                break;
            case JUSQUA_ERR:
                messageErr+="expected jusqua  ==> found "+courant;
                break;
            case POUR_ERR:
                messageErr+="expected pour  ==> found "+courant;
                break;
            case FINPOUR_ERR:
                messageErr+="expected finpour  ==> found "+courant;
                break;
            case MOD_ERR:
                messageErr+="expected mod  ==> found "+courant;
                break;
            case NUM_ERR:
                messageErr+="expected nombre  ==> found "+courant;
                break;
            case PV_ERR:
                messageErr+="expected ;  ==> found "+courant;
                break;
            case PLUS_ERR:
                messageErr+="expected +  ==> found "+courant;
                break;
            case MOINS_ERR:
                messageErr+="expected -  ==> found "+courant;
                break;
            case MULT_ERR:
                messageErr+="expected *  ==> found "+courant;
                break;
            case DIV_ERR:
                messageErr+="expected /  ==> found "+courant;
                break;
            case VIR_ERR:
                messageErr+="expected ,  ==> found "+courant;
                break;
            case AFF_ERR:
                messageErr+="expected :=  ==> found "+courant;
                break;
            case INF_ERR:
                messageErr+="expected <  ==> found "+courant;
                break;
            case SUP_ERR:
                messageErr+="expected >  ==> found "+courant;
                break;
            case INFEG_ERR:
                messageErr+="expected <=  ==> found "+courant;
                break;
            case SUPEG_ERR:
                messageErr+="expected >=  ==> found "+courant;
                break;
            case DIFF_ERR:
                messageErr+="expected <>  ==> found "+courant;
                break;
            case PO_ERR:
                messageErr+="expected (  ==> found "+courant;
                break;
            case PF_ERR:
                messageErr+="expected )  ==> found "+courant;
                break;
            case EG_ERR:
                messageErr+="expected =  ==> found "+courant;
                break;
            case CRO_ERR:
                messageErr+="expected [  ==> found "+courant;
                break;
            case CRF_ERR:
                messageErr+="expected ]  ==> found "+courant;
                break;
            case DEUX_POINTS_ERR:
                messageErr+="expected :  ==> found "+courant;
                break;
            case CHAINE_CONSTANTE_ERR:
                messageErr+="expected une chaine de caractère constante   ==> found "+courant;
                break;
            case CAR_CONSTANTE_ERR:
                messageErr+="expected caractère  ==> found "+courant;
                break;
            case OU_ERR:
                messageErr+="expected ou  ==> found "+courant;
                break;
            case ET_ERR:
                messageErr+="expected et  ==> found "+courant;
                break;
            case NON_ERR:
                messageErr+="expected non  ==> found "+courant;
                break;
            case FIN_CAS_ERR:
                messageErr+="expected fincas  ==> found "+courant;
                break;
            case PROGRAM_ERR:
                messageErr+="expected fonction or identifiant   ==> found "+courant;
                break;
            case PROGRAM2_ERR:
                messageErr+="expected fonction or identifiant  ==> found "+courant;
                break;
            case EXTERNAL_DECLARATION_ERR:
                messageErr+="expected fonction or identifiant  ==> found "+courant;
                break;
            case FUNCTION_DEFINITION_ERR:
                messageErr+="expected fonction  ==> found "+courant;
                break;
            case PARAMETER_DECLARATION_ERR:
                messageErr+="expected identifiant  ==> found "+courant;
                break;
            case PLUS_PARAMETRES_ERR:
                messageErr+="expected ,  ==> found "+courant;
                break;
            case DECLARATION_ERR:
                messageErr+="expected identifiant  ==> found "+courant;
                break;
            case DECLARATION2_ERR:
                messageErr+="expected ; or :=  ==> found "+courant;
                break;
            case VALUE_ERR:
                messageErr+="expected nombre or caractère or chaine de caractère or identifiant ==> found "+courant;
                break;
            case APPEL_FONCTION_ERR:
                messageErr+="expected identifiant  ==> found "+courant;
                break;
            case ARGUMENT_LIST_ERR:
                messageErr+="expected nombre or caractère or chaine de caractère or identifiant ==> found "+courant;
                break;
            case VALUE_EPSILON_ERR:
                messageErr+="expected ,  ==> found "+courant;
                break;
            case COMPOUND_STATEMENT_ERR:
                messageErr+="expected debut  ==> found "+courant;
                break;
            case EXPRESSION_STATEMENT_ERR:
                messageErr+="expected ( or identifiant or nombre or caractère or chaine de caractère or identifiant   ==> found "+courant;
                break;
            case EXPRESSION_STATEMENT2_ERR:
                messageErr+="expected : or := or [ or (  ==> found "+courant;
                break;
            case STATEMENT_ERR:
                messageErr+="expected si or selon or tantque or pour or repeter or retourner or ( or identifiant or nombre or caractère or chaine de caractère or identifiant  ==> found "+courant;
                break;
            case CONDITIONAL_STATEMENT_ERR:
                messageErr+="expected si or selon  ==> found "+courant;
                break;
            case SI_STATEMENT_ERR:
                messageErr+="expected  si  ==> found "+courant;
                break;
            case SINONSTATEMENT_ERR:
                messageErr+="expected sinon  ==> found "+courant;
                break;
            case SELON_STATEMENT_ERR:
                messageErr+="expected selon  ==> found "+courant;
                break;
            case CASE_ERR:
                messageErr+="expected entier  ==> found "+courant;
                break;
            case LOOP_STATEMENT_ERR:
                messageErr+="expected tantque or pour or repeter  ==> found "+courant;
                break;
            case TANQUE_STATEMENT_ERR:
                messageErr+="expected tantque  ==> found "+courant;
                break;
            case POUR_STATEMENT_ERR:
                messageErr+="expected pour  ==> found "+courant;
                break;
            case REPETER_STATEMENT_ERR:
                messageErr+="expected repeter  ==> found "+courant;
                break;
            case INITIALISATION_EXPRESSION_ERR:
                messageErr+="expected identifiant   ==> found "+courant;
                break;
            case AUTRE_INITIALISATIONS_ERR:
                messageErr+="expected ,  ==> found "+courant;
                break;
            case AFFECTATION_EXPRESSION_ERR:
                messageErr+="expected identifiant  ==> found "+courant;
                break;
            case AFFECTATION_EXPRESSION2_ERR:
                messageErr+="expected identifiant nombre or caractère or chaine de caractère  ==> found "+courant;
                break;
            case TEST_EXPRESSION_ERR:
                messageErr+="expected non or ( or identifiant or nombre or caractère or chaine de caractère   ==> found "+courant;
                break;
            case MULTIPLICATIVE_EXPRESSION_ERR:
                messageErr+="expected ( or nombre or caractère or chaine de caractère  or identifiant  ==> found "+courant;
                break;
            case POSTFIX_EXPRESSION_ERR:
                messageErr+="expected ( or nombre or caractère or chaine de caractère  or identifiant  ==> found "+courant;
                break;
            case MULTIPLICATIVE_EXPRESSION2_ERR:
                messageErr+="expected * or / or mod  ==> found "+courant;
                break;
            case ADDITIVE_EXPRESSION_ERR:
                messageErr+="expected ( or nombre or caractère or chaine de caractère  or identifiant  ==> found "+courant;
                break;
            case LOGICAL_OU_EXPRESSION_ERR:
                messageErr+="expected ( ou nombre or caractère or chaine de caractère  or identifiant  ==> found "+courant;
                break;
            case LOGICAL_OU_EXPRESSION2_ERR:
                messageErr+="expected ou  ==> found "+courant;
                break;
            case LOGICAL_ET_EXPRESSION_ERR:
                messageErr+="expected (  or nombre or caractère or chaine de caractère or identifiant  ==> found "+courant;
                break;
            case LOGICAL_ET_EXPRESSION2_ERR:
                messageErr+="expected et  ==> found "+courant;
                break;
            case EQUALITY_EXPRESSION_ERR:
                messageErr+="expected (  or nombre or caractère or chaine de caractère or identifiant  ==> found "+courant;
                break;
            case EQUALITY_EXPRESSION2_ERR:
                messageErr+="expected <> or =  ==> found "+courant;
                break;
            case RELATIONAL_EXPRESSION_ERR:
                messageErr+="expected (  or nombre or caractère or chaine de caractère or identifiant  ==> found "+courant;
                break;
            case RELATIONAL_EXPRESSION2_ERR:
                messageErr+="expected < or > or <= or >=  ==> found "+courant;
                break;
            case TAB_DIM_MULT_ERR:
                messageErr+="expected [  ==> found "+courant;
                break;
            case TAB_FACTOR_ERR:
                messageErr+="expected [  ==> found "+courant;
                break;
            case RETURN_STATEMENT_ERR:
                messageErr+="expected retourner  ==> found "+courant;
                break;
            case VAL_RETOURNER_ERR:
                messageErr+="expected ( or identifiant or nombre or caractère or chaine de caractère  ==> found "+courant;
                break;
            case IDENTIFIER2_ERR:
                messageErr+="expected [ or (  ==> found "+courant;
                break;
        }
        return messageErr;
    }

    public void ERROR(String courant,ERR_SYNTAX ERR ){

      String messageErr = "";
      messageErr = getMessage(courant,ERR);
      System.err.println("[Syntax Error]: "+messageErr);
      System.exit(-1);
    }

    public  boolean analyseSyntax() throws Exception{
       Sym_Suiv();
       PROGRAM();
       if(CODE_LEX_Cour==CODES_LEX.FIN_TOKEN){
        return true;
       }else{
        return false;
       }
    }



    @Override
    public void PROGRAM() throws Exception{
     if(CODE_LEX_Cour == CODES_LEX.FONCTION_TOKEN || CODE_LEX_Cour == CODES_LEX.ID_TOKEN){
      EXTERNAL_DECLARATION();
      PROGRAM2();
     }else{
       ERROR(courant, ERR_SYNTAX.PROGRAM_ERR);
     }
    }

    @Override
    public void PROGRAM2() throws Exception{
     if(CODE_LEX_Cour==CODES_LEX.FONCTION_TOKEN || CODE_LEX_Cour == CODES_LEX.ID_TOKEN ){
      PROGRAM();
     }
    }

    @Override
    public void EXTERNAL_DECLARATION() throws Exception {
      if(CODE_LEX_Cour==CODES_LEX.FONCTION_TOKEN){
       FUNCTION_DEFINITION();
      }else if(CODE_LEX_Cour==CODES_LEX.ID_TOKEN){
       DECLARATION();
      }else{
        ERROR(courant, ERR_SYNTAX.EXTERNAL_DECLARATION_ERR);
      }
    }

    @Override
    public void FUNCTION_DEFINITION() throws Exception {
        if(CODE_LEX_Cour==CODES_LEX.FONCTION_TOKEN){
            Test_Symbole(CODES_LEX.FONCTION_TOKEN,ERR_SYNTAX.FONCTION_ERR);
            Test_Symbole(CODES_LEX.ID_TOKEN,ERR_SYNTAX.ID_ERR);
            Test_Symbole(CODES_LEX.PO_TOKEN,ERR_SYNTAX.PO_ERR);
            PARAMETER_DECLARATION();
            Test_Symbole(CODES_LEX.PF_TOKEN,ERR_SYNTAX.PF_ERR);
            Test_Symbole(CODES_LEX.DEUX_POINTS_TOKEN,ERR_SYNTAX.DEUX_POINTS_ERR);
            TYPE_SPECIFIER();
            COMPOUND_STATEMENT();
        }else{
            ERROR(courant,ERR_SYNTAX.FUNCTION_DEFINITION_ERR);
        }

    }

    @Override
    public void PARAMETER_DECLARATION() throws Exception{
        switch (CODE_LEX_Cour){
            case ID_TOKEN:
                Test_Symbole(CODES_LEX.ID_TOKEN,ERR_SYNTAX.ID_ERR);
                Test_Symbole(CODES_LEX.DEUX_POINTS_TOKEN,ERR_SYNTAX.DEUX_POINTS_ERR);
                TYPE_SPECIFIER();
                PLUS_PARAMETRES();
                break;
            case PF_TOKEN: break;
            default: ERROR(courant, ERR_SYNTAX.PARAMETER_DECLARATION_ERR); break;
        }
    }

    @Override
    public void PLUS_PARAMETRES() throws Exception {
        switch (CODE_LEX_Cour){
            case VIR_TOKEN:
                Test_Symbole(CODES_LEX.VIR_TOKEN,ERR_SYNTAX.VIR_ERR);
                PARAMETER_DECLARATION();
                break;
            case PF_TOKEN: break;
            default: ERROR(courant, ERR_SYNTAX.PLUS_PARAMETRES_ERR); break;
        }
    }

    @Override
    public void DECLARATION() throws Exception {
        if(CODE_LEX_Cour==CODES_LEX.ID_TOKEN) {
            Test_Symbole(CODES_LEX.ID_TOKEN, ERR_SYNTAX.ID_ERR);
            Test_Symbole(CODES_LEX.DEUX_POINTS_TOKEN, ERR_SYNTAX.DEUX_POINTS_ERR);
            TYPE_SPECIFIER();
            DECLARATION2();
        }else{
            ERROR(courant,ERR_SYNTAX.DECLARATION_ERR);
        }
    }

    @Override
    public void DECLARATION2() throws Exception {
       if(CODE_LEX_Cour==CODES_LEX.PV_TOKEN){
        Test_Symbole(CODES_LEX.PV_TOKEN,ERR_SYNTAX.PV_ERR);
       }else if(CODE_LEX_Cour==CODES_LEX.AFF_TOKEN){
        Test_Symbole(CODES_LEX.AFF_TOKEN,ERR_SYNTAX.AFF_ERR);
        VALUE();
        Test_Symbole(CODES_LEX.PV_TOKEN,ERR_SYNTAX.PV_ERR);
       }else{
        ERROR(courant, ERR_SYNTAX.DECLARATION2_ERR);
       }
    }

    @Override
    public void VALUE() throws Exception{
      if(CODE_LEX_Cour == CODES_LEX.ID_TOKEN){
       APPEL_FONCTION();
      }else if(CODE_LEX_Cour==CODES_LEX.NUM_TOKEN){
       Test_Symbole(CODES_LEX.NUM_TOKEN,ERR_SYNTAX.NUM_ERR);
      }else if(CODE_LEX_Cour==CODES_LEX.CAR_CONSTANTE){
       Test_Symbole(CODES_LEX.CAR_CONSTANTE,ERR_SYNTAX.CAR_CONSTANTE_ERR);
      }else if(CODE_LEX_Cour==CODES_LEX.CHAINE_CONSTANTE){
       Test_Symbole(CODES_LEX.CHAINE_CONSTANTE,ERR_SYNTAX.CHAINE_CONSTANTE_ERR);
      }else{
          ERROR(courant, ERR_SYNTAX.VALUE_ERR);
      }
    }

    @Override
    public void APPEL_FONCTION() throws Exception {
        switch (CODE_LEX_Cour){
            case ID_TOKEN:
                Test_Symbole(CODES_LEX.ID_TOKEN,ERR_SYNTAX.ID_ERR);
                APPEL_FONCTION2();
//                Test_Symbole(CODES_LEX.PO_TOKEN,ERR_SYNTAX.PO_ERR);
//                ARGUMENT_LIST();
//                Test_Symbole(CODES_LEX.PF_TOKEN,ERR_SYNTAX.PF_ERR);
                break;
            default:
                ERROR(courant, ERR_SYNTAX.APPEL_FONCTION_ERR);
                break;
        }

    }

    @Override
    public void APPEL_FONCTION2() throws Exception {
        switch (CODE_LEX_Cour){
            case PO_TOKEN:
                Test_Symbole(CODES_LEX.PO_TOKEN, ERR_SYNTAX.PO_ERR);
                ARGUMENT_LIST();
                Test_Symbole(CODES_LEX.PF_TOKEN, ERR_SYNTAX.PF_ERR);
                break;
            case PF_TOKEN:
            case VIR_TOKEN:
            case PV_TOKEN:
                break;
            default:
                ERROR(courant, ERR_SYNTAX.APPEL_FONCTION2_ERR);
                break;
        }
    }

    @Override
    public void ARGUMENT_LIST() throws Exception{
        switch (CODE_LEX_Cour){
            case ID_TOKEN:
            case NUM_TOKEN:
            case CAR_CONSTANTE:
            case CHAINE_CONSTANTE:
                VALUE();
                VALUE_EPSILON();
                break;
            case PF_TOKEN: break;
            default: ERROR(courant, ERR_SYNTAX.ARGUMENT_LIST_ERR);break;
        }
//     if(CODE_LEX_Cour == CODES_LEX.ID_TOKEN){
//      VALUE();
//      VALUE_EPSILON();
//     }else if(CODE_LEX_Cour==CODES_LEX.NUM_TOKEN){
//      VALUE();
//      VALUE_EPSILON();
//     }else if(CODE_LEX_Cour==CODES_LEX.CAR_CONSTANTE){
//      VALUE();
//      VALUE_EPSILON();
//     }else if(CODE_LEX_Cour==CODES_LEX.CHAINE_CONSTANTE){
//      VALUE();
//      VALUE_EPSILON();
//     }
    }

    @Override
    public void VALUE_EPSILON() throws Exception{
        switch(CODE_LEX_Cour){
            case VIR_TOKEN:
                Test_Symbole(CODES_LEX.VIR_TOKEN,ERR_SYNTAX.VIR_ERR);
                VALUE();
                VALUE_EPSILON();
                break;
            case PF_TOKEN: break;
            default:ERROR(courant, ERR_SYNTAX.VALUE_EPSILON_ERR); break;
        }
    }

    @Override
    public void COMPOUND_STATEMENT() throws Exception{
       if(CODE_LEX_Cour == CODES_LEX.DEBUT_TOKEN){
           Test_Symbole(CODES_LEX.DEBUT_TOKEN,ERR_SYNTAX.DEBUT_ERR);
           STATEMENT();
           Test_Symbole(CODES_LEX.FIN_TOKEN,ERR_SYNTAX.FIN_ERR);
       }else{
           ERROR(courant,ERR_SYNTAX.COMPOUND_STATEMENT_ERR);
       }
    }

    @Override
    public void STATEMENT() throws Exception{

        switch (CODE_LEX_Cour){
            case ID_TOKEN:
            case NUM_TOKEN:
            case PO_TOKEN:
                EXPRESSION_STATEMENT();
                STATEMENT();
                break;
            case SI_TOKEN:
            case SELON_TOKEN:
                CONDITIONAL_STATEMENT();
                STATEMENT();
                break;
            case TANT_QUE_TOKEN:
            case POUR_TOKEN:
            case REPETER_TOKEN:
                LOOP_STATEMENT();
                STATEMENT();

                break;
            case RETOURNER_TOKEN:
                RETURN_STATEMENT();
                STATEMENT();

                break;
            case FIN_TOKEN:
            case FINTQ_TOKEN:
            case FINPOUR_TOKEN:
            case JUSQUA_TOKEN:
            case FIN_CAS_TOKEN:
            case FINSI_TOKEN:
            case SINON_TOKEN:
            case FINSELON_TOKEN:
                break;
            default: ERROR(courant, ERR_SYNTAX.STATEMENT_ERR); break;
        }
//     if(CODES_LEX.ID_TOKEN == CODE_LEX_Cour  || CODES_LEX.NUM_TOKEN ==CODE_LEX_Cour || CODES_LEX.PO_TOKEN==CODE_LEX_Cour){
//      EXPRESSION_STATEMENT();
//     }else if(CODE_LEX_Cour==CODES_LEX.SI_TOKEN || CODE_LEX_Cour==CODES_LEX.SELON_TOKEN){
//      CONDITIONAL_STATEMENT();
//     }else if(CODE_LEX_Cour==CODES_LEX.TANT_QUE_TOKEN || CODE_LEX_Cour==CODES_LEX.POUR_TOKEN || CODE_LEX_Cour==CODES_LEX.REPETER_TOKEN){
//      LOOP_STATEMENT();
//     }else if(CODES_LEX.RETOURNER_TOKEN == CODE_LEX_Cour){
//      RETURN_STATEMENT();
//     }else{
//      //ERROR(courant, ERR_SYNTAX.STATEMENT_ERR);
//     }
    }

    @Override
    public void EXPRESSION_STATEMENT() throws Exception{
       if( CODES_LEX.ID_TOKEN == CODE_LEX_Cour ){
        Test_Symbole(CODES_LEX.ID_TOKEN,ERR_SYNTAX.ID_ERR);
        EXPRESSION_STATEMENT2();
       }else if(CODES_LEX.NUM_TOKEN == CODE_LEX_Cour){
        Test_Symbole(CODES_LEX.NUM_TOKEN,ERR_SYNTAX.NUM_ERR);
       }else if(CODES_LEX.PO_TOKEN == CODE_LEX_Cour){
        Test_Symbole(CODES_LEX.PO_TOKEN,ERR_SYNTAX.PO_ERR);
        TEST_EXPRESSION();
        Test_Symbole(CODES_LEX.PF_TOKEN,ERR_SYNTAX.PF_ERR);
       }else{
        ERROR(courant, ERR_SYNTAX.EXPRESSION_STATEMENT_ERR);
       }
    }

    @Override
    public void EXPRESSION_STATEMENT2() throws Exception{
       if(CODES_LEX.AFF_TOKEN==CODE_LEX_Cour){
        Test_Symbole(CODES_LEX.AFF_TOKEN,ERR_SYNTAX.AFF_ERR);
        AFFECTATION_EXPRESSION2();
        Test_Symbole(CODES_LEX.PV_TOKEN,ERR_SYNTAX.PV_ERR);
       }else if(CODES_LEX.CRO_TOKEN == CODE_LEX_Cour){
        Test_Symbole(CODES_LEX.CRO_TOKEN,ERR_SYNTAX.CRO_ERR);
        ADDITIVE_EXPRESSION();
        Test_Symbole(CODES_LEX.CRF_TOKEN,ERR_SYNTAX.CRF_ERR);
        Test_Symbole(CODES_LEX.AFF_TOKEN,ERR_SYNTAX.AFF_ERR);
        AFFECTATION_EXPRESSION2();
        Test_Symbole(CODES_LEX.PV_TOKEN,ERR_SYNTAX.PV_ERR);
       }else if(CODES_LEX.DEUX_POINTS_TOKEN==CODE_LEX_Cour){
        Test_Symbole(CODES_LEX.DEUX_POINTS_TOKEN,ERR_SYNTAX.DEUX_POINTS_ERR);
        TYPE_SPECIFIER();
        DECLARATION2();
       }else if(CODES_LEX.PO_TOKEN == CODE_LEX_Cour){
           Test_Symbole(CODES_LEX.PO_TOKEN, ERR_SYNTAX.PO_ERR);
           ARGUMENT_LIST();
           Test_Symbole(CODES_LEX.PF_TOKEN, ERR_SYNTAX.PF_ERR);
           Test_Symbole(CODES_LEX.PV_TOKEN, ERR_SYNTAX.PV_ERR);
        }
       else{
        ERROR(courant, ERR_SYNTAX.EXPRESSION_STATEMENT2_ERR);
       }
    }

    @Override
    public void CONDITIONAL_STATEMENT() throws Exception{
        if(CODE_LEX_Cour==CODES_LEX.SI_TOKEN){
         SI_STATEMENT();
        }else if(CODE_LEX_Cour== CODES_LEX.SELON_TOKEN){
         SELON_STATEMENT();
        }else{
         ERROR(courant, ERR_SYNTAX.CONDITIONAL_STATEMENT_ERR);
        }
    }

    @Override
    public void SI_STATEMENT() throws Exception{
        if(CODE_LEX_Cour==CODES_LEX.SI_TOKEN){
            Test_Symbole(CODES_LEX.SI_TOKEN,ERR_SYNTAX.SI_ERR);
            Test_Symbole(CODES_LEX.PO_TOKEN,ERR_SYNTAX.PO_ERR);
            TEST_EXPRESSION();
            Test_Symbole(CODES_LEX.PF_TOKEN,ERR_SYNTAX.PF_ERR);
            Test_Symbole(CODES_LEX.ALORS_TOKEN,ERR_SYNTAX.ALORS_ERR);
            STATEMENT();
            SINONSTATEMENT();
            Test_Symbole(CODES_LEX.FINSI_TOKEN,ERR_SYNTAX.FINSI_ERR);
        }else{
            ERROR(courant,ERR_SYNTAX.SI_STATEMENT_ERR);
        }

    }

    @Override
    public void SINONSTATEMENT() throws Exception{
        switch (CODE_LEX_Cour){
            case SINON_TOKEN:
                Test_Symbole(CODES_LEX.SINON_TOKEN,ERR_SYNTAX.SINON_ERR);
                STATEMENT();
                break;
            case FINSI_TOKEN:break;
            default: ERROR(courant, ERR_SYNTAX.SINONSTATEMENT_ERR); break;

        }
    }

    @Override
    public void SELON_STATEMENT() throws Exception{
        if(CODE_LEX_Cour==CODES_LEX.SELON_TOKEN){
            Test_Symbole(CODES_LEX.SELON_TOKEN,ERR_SYNTAX.SELON_ERR);
            Test_Symbole(CODES_LEX.PO_TOKEN,ERR_SYNTAX.PO_ERR);
            Test_Symbole(CODES_LEX.ID_TOKEN,ERR_SYNTAX.ID_ERR);
            Test_Symbole(CODES_LEX.PF_TOKEN,ERR_SYNTAX.PF_ERR);
            Test_Symbole(CODES_LEX.FAIRE_TOKEN,ERR_SYNTAX.FAIRE_ERR);
            CASE();
            SELON_FACTORI();
            Test_Symbole(CODES_LEX.FINSELON_TOKEN,ERR_SYNTAX.FINSELON_ERR);
        }else{
            ERROR(courant,ERR_SYNTAX.SELON_STATEMENT_ERR);
        }
    }

    @Override
    public void SELON_FACTORI() throws Exception{

    switch (CODE_LEX_Cour){
        case SINON_TOKEN :
            Test_Symbole(CODES_LEX.SINON_TOKEN,ERR_SYNTAX.SINON_ERR);
            Test_Symbole(CODES_LEX.DEUX_POINTS_TOKEN,ERR_SYNTAX.DEUX_POINTS_ERR);
            STATEMENT();
            break;
        case FINSELON_TOKEN:
            break;
        default:
            ERROR(courant, ERR_SYNTAX.SELON_FACTORI_ERR);
            break;

    }

    }

    @Override
    public void CASE() throws Exception{
        switch (CODE_LEX_Cour){
            case CAR_CONSTANTE:
                Test_Symbole(CODES_LEX.CAR_CONSTANTE, ERR_SYNTAX.CAR_ERR);
                Test_Symbole(CODES_LEX.DEUX_POINTS_TOKEN,ERR_SYNTAX.DEUX_POINTS_ERR);
                STATEMENT();
                Test_Symbole(CODES_LEX.FIN_CAS_TOKEN,ERR_SYNTAX.FIN_CAS_ERR);
                CASE();
                break;
            case NUM_TOKEN:
                Test_Symbole(CODES_LEX.NUM_TOKEN, ERR_SYNTAX.NUM_ERR);
                Test_Symbole(CODES_LEX.DEUX_POINTS_TOKEN,ERR_SYNTAX.DEUX_POINTS_ERR);
                STATEMENT();
                Test_Symbole(CODES_LEX.FIN_CAS_TOKEN,ERR_SYNTAX.FIN_CAS_ERR);
                CASE();
                break;

            case ID_TOKEN:
                Test_Symbole(CODES_LEX.ID_TOKEN, ERR_SYNTAX.ID_ERR);
                Test_Symbole(CODES_LEX.DEUX_POINTS_TOKEN,ERR_SYNTAX.DEUX_POINTS_ERR);
                STATEMENT();
                Test_Symbole(CODES_LEX.FIN_CAS_TOKEN,ERR_SYNTAX.FIN_CAS_ERR);
                CASE();
                break;
            case SINON_TOKEN:
            case FINSELON_TOKEN:
                break;
            default:
                ERROR(courant, ERR_SYNTAX.CASE_ERR);
                break;
        }

    }

    @Override
    public void LOOP_STATEMENT() throws Exception{
     if(CODE_LEX_Cour==CODES_LEX.TANT_QUE_TOKEN){
      TANQUE_STATEMENT();
     }else if(CODES_LEX.POUR_TOKEN==CODE_LEX_Cour){
      POUR_STATEMENT();
     }else if(CODES_LEX.REPETER_TOKEN==CODE_LEX_Cour){
      REPETER_STATEMENT();
     }else {
      ERROR(courant, ERR_SYNTAX.LOOP_STATEMENT_ERR);
     }
    }

    @Override
    public void TANQUE_STATEMENT()throws Exception {
        if(CODE_LEX_Cour==CODES_LEX.TANT_QUE_TOKEN){
            Test_Symbole(CODES_LEX.TANT_QUE_TOKEN,ERR_SYNTAX.TANT_QUE_ERR);
            Test_Symbole(CODES_LEX.PO_TOKEN,ERR_SYNTAX.PO_ERR);
            TEST_EXPRESSION();
            Test_Symbole(CODES_LEX.PF_TOKEN,ERR_SYNTAX.PF_ERR);
            Test_Symbole(CODES_LEX.FAIRE_TOKEN,ERR_SYNTAX.FAIRE_ERR);
            STATEMENT();
            Test_Symbole(CODES_LEX.FINTQ_TOKEN,ERR_SYNTAX.FINTQ_ERR);
        }else{
            ERROR(courant,ERR_SYNTAX.TANQUE_STATEMENT_ERR);
        }

    }

    @Override
    public void POUR_STATEMENT() throws Exception {
        if(CODE_LEX_Cour==CODES_LEX.POUR_TOKEN){
            Test_Symbole(CODES_LEX.POUR_TOKEN,ERR_SYNTAX.POUR_ERR);
            Test_Symbole(CODES_LEX.PO_TOKEN,ERR_SYNTAX.PO_ERR);
            INITIALISATION_EXPRESSION();
            Test_Symbole(CODES_LEX.PV_TOKEN,ERR_SYNTAX.PV_ERR);
            TEST_EXPRESSION();
            Test_Symbole(CODES_LEX.PV_TOKEN,ERR_SYNTAX.PV_ERR);
            AFFECTATION_EXPRESSION();
            Test_Symbole(CODES_LEX.PF_TOKEN,ERR_SYNTAX.PF_ERR);
            Test_Symbole(CODES_LEX.FAIRE_TOKEN,ERR_SYNTAX.FAIRE_ERR);
            STATEMENT();
            Test_Symbole(CODES_LEX.FINPOUR_TOKEN,ERR_SYNTAX.FINPOUR_ERR);
        }else{
            ERROR(courant,ERR_SYNTAX.POUR_STATEMENT_ERR);
        }

    }

    @Override
    public void REPETER_STATEMENT() throws Exception {
        if(CODE_LEX_Cour==CODES_LEX.REPETER_TOKEN){
            Test_Symbole(CODES_LEX.REPETER_TOKEN,ERR_SYNTAX.REPETER_ERR);
            STATEMENT();
            Test_Symbole(CODES_LEX.JUSQUA_TOKEN,ERR_SYNTAX.JUSQUA_ERR);
            Test_Symbole(CODES_LEX.PO_TOKEN,ERR_SYNTAX.PO_ERR);
            TEST_EXPRESSION();
            Test_Symbole(CODES_LEX.PF_TOKEN,ERR_SYNTAX.PF_ERR);
            Test_Symbole(CODES_LEX.PV_TOKEN,ERR_SYNTAX.PV_ERR);
        }else{
            ERROR(courant,ERR_SYNTAX.REPETER_STATEMENT_ERR);
        }
    }

    @Override
    public void INITIALISATION_EXPRESSION() throws Exception {
        switch (CODE_LEX_Cour){
            case ID_TOKEN:
                Test_Symbole(CODES_LEX.ID_TOKEN,ERR_SYNTAX.ID_ERR);
                Test_Symbole(CODES_LEX.AFF_TOKEN,ERR_SYNTAX.AFF_ERR);
                VALUE();
                AUTRE_INITIALISATIONS();
                break;
            case PV_TOKEN:
                break;
            default:
                ERROR(courant, ERR_SYNTAX.INITIALISATION_EXPRESSION_ERR);
                break;
        }

    }

    @Override
    public void AUTRE_INITIALISATIONS() throws Exception{
      switch (CODE_LEX_Cour){
          case VIR_TOKEN:
              Test_Symbole(CODES_LEX.VIR_TOKEN,ERR_SYNTAX.VIR_ERR);
              Test_Symbole(CODES_LEX.ID_TOKEN,ERR_SYNTAX.ID_ERR);
              Test_Symbole(CODES_LEX.AFF_TOKEN,ERR_SYNTAX.AFF_ERR);
              VALUE();
              AUTRE_INITIALISATIONS();
          break;
          case PV_TOKEN:
              break;
          default:
              ERROR(courant, ERR_SYNTAX.AUTRE_INITIALISATIONS_ERR);
              break;
      }
    }

    @Override
    public void AFFECTATION_EXPRESSION() throws Exception{
        if(CODE_LEX_Cour==CODES_LEX.ID_TOKEN){
            Test_Symbole(CODES_LEX.ID_TOKEN,ERR_SYNTAX.ID_ERR);
            Test_Symbole(CODES_LEX.AFF_TOKEN,ERR_SYNTAX.AFF_ERR);
            AFFECTATION_EXPRESSION2();
        }else{
            ERROR(courant,ERR_SYNTAX.AFFECTATION_EXPRESSION_ERR);
        }

    }

    @Override
    public void AFFECTATION_EXPRESSION2() throws Exception{
        switch (CODE_LEX_Cour){
            case NUM_TOKEN:
            case ID_TOKEN:
            case PO_TOKEN:
            case CAR_CONSTANTE:
            case CHAINE_CONSTANTE:
                ADDITIVE_EXPRESSION();
                break;
            default:
                ERROR(courant, ERR_SYNTAX.AFFECTATION_EXPRESSION2_ERR);
                break;

        }

    }

    @Override
    public void TEST_EXPRESSION() throws Exception{

        switch (CODE_LEX_Cour){
            case ID_TOKEN:
            case NUM_TOKEN:
            case PO_TOKEN:
                LOGICAL_OU_EXPRESSION();
                break;
            case NON_TOKEN:
                Test_Symbole(CODES_LEX.NON_TOKEN,ERR_SYNTAX.NON_ERR);
                Test_Symbole(CODES_LEX.PO_TOKEN,ERR_SYNTAX.PO_ERR);
                LOGICAL_OU_EXPRESSION();
                Test_Symbole(CODES_LEX.PF_TOKEN,ERR_SYNTAX.PF_ERR);
                break;
            case PF_TOKEN:
            case PV_TOKEN:
                break;
            default:
                ERROR(courant, ERR_SYNTAX.TEST_EXPRESSION_ERR);
                break;

        }

    }


    @Override
    public void LOGICAL_OU_EXPRESSION() throws Exception{
        if(CODE_LEX_Cour==CODES_LEX.ID_TOKEN ||
                CODE_LEX_Cour==CODES_LEX.NUM_TOKEN || CODE_LEX_Cour==CODES_LEX.PO_TOKEN
                || CODE_LEX_Cour == CODES_LEX.CAR_CONSTANTE || CODE_LEX_Cour == CODES_LEX.CHAINE_CONSTANTE

        ){
            LOGICAL_ET_EXPRESSION();
            LOGICAL_OU_EXPRESSION2();
     }else{
      ERROR(courant, ERR_SYNTAX.LOGICAL_OU_EXPRESSION_ERR);
     }
    }

    @Override
    public void LOGICAL_OU_EXPRESSION2() throws Exception{

     switch (CODE_LEX_Cour){
         case OU_TOKEN:
             Test_Symbole(CODES_LEX.OU_TOKEN,ERR_SYNTAX.OU_ERR);
             LOGICAL_ET_EXPRESSION();
             LOGICAL_OU_EXPRESSION2();
             break;
         case PV_TOKEN:
         case PF_TOKEN:
             break;
         default:
             ERROR(courant, ERR_SYNTAX.LOGICAL_OU_EXPRESSION2_ERR);
             break;
     }

    }

 @Override
   public void LOGICAL_ET_EXPRESSION() throws Exception {
     if(CODE_LEX_Cour==CODES_LEX.ID_TOKEN ||
             CODE_LEX_Cour==CODES_LEX.NUM_TOKEN || CODE_LEX_Cour==CODES_LEX.PO_TOKEN
             || CODE_LEX_Cour == CODES_LEX.CAR_CONSTANTE || CODE_LEX_Cour == CODES_LEX.CHAINE_CONSTANTE

     ) {
         EQUALITY_EXPRESSION();
     LOGICAL_ET_EXPRESSION2();
     }else{
     ERROR(courant, ERR_SYNTAX.LOGICAL_ET_EXPRESSION_ERR);
     }
    }

 @Override
  public void LOGICAL_ET_EXPRESSION2() throws Exception {

        switch (CODE_LEX_Cour){
            case ET_TOKEN:
                Test_Symbole(CODES_LEX.ET_TOKEN,ERR_SYNTAX.ET_ERR);
                EQUALITY_EXPRESSION();
                LOGICAL_ET_EXPRESSION2();
                break;
            case OU_TOKEN:
            case PF_TOKEN:
            case PV_TOKEN:
                break;
            default:
                ERROR(courant, ERR_SYNTAX.LOGICAL_ET_EXPRESSION2_ERR);
                break;

        }

 }

 @Override
    public void EQUALITY_EXPRESSION() throws Exception{
     if(CODE_LEX_Cour==CODES_LEX.ID_TOKEN ||
             CODE_LEX_Cour==CODES_LEX.NUM_TOKEN || CODE_LEX_Cour==CODES_LEX.PO_TOKEN
             || CODE_LEX_Cour == CODES_LEX.CAR_CONSTANTE || CODE_LEX_Cour == CODES_LEX.CHAINE_CONSTANTE

     ){
         RELATIONAL_EXPRESSION();
     EQUALITY_EXPRESSION2();
     }else{
      ERROR(courant, ERR_SYNTAX.EQUALITY_EXPRESSION_ERR);
    }
    }

    @Override
    public void EQUALITY_EXPRESSION2() throws Exception{
        switch (CODE_LEX_Cour){
            case DIFF_TOKEN:
                Test_Symbole(CODES_LEX.DIFF_TOKEN,ERR_SYNTAX.DIFF_ERR);
                RELATIONAL_EXPRESSION();
                EQUALITY_EXPRESSION2();
                break;
            case EG_TOKEN:
                Test_Symbole(CODES_LEX.EG_TOKEN,ERR_SYNTAX.EG_ERR);
                RELATIONAL_EXPRESSION();
                EQUALITY_EXPRESSION2();
            case OU_TOKEN:
            case ET_TOKEN:
            case PF_TOKEN:
            case PV_TOKEN:
                break;
            default:
                ERROR(courant, ERR_SYNTAX.EQUALITY_EXPRESSION2_ERR);
                break;


        }

    }

    @Override
    public void RELATIONAL_EXPRESSION() throws Exception{
        if(CODE_LEX_Cour==CODES_LEX.ID_TOKEN ||
                CODE_LEX_Cour==CODES_LEX.NUM_TOKEN || CODE_LEX_Cour==CODES_LEX.PO_TOKEN
                || CODE_LEX_Cour == CODES_LEX.CAR_CONSTANTE || CODE_LEX_Cour == CODES_LEX.CHAINE_CONSTANTE

        ){
            ADDITIVE_EXPRESSION();
      RELATIONAL_EXPRESSION2();
     }else{
      ERROR(courant, ERR_SYNTAX.RELATIONAL_EXPRESSION_ERR);
     }
    }

    @Override
    public void RELATIONAL_EXPRESSION2 () throws Exception{
        switch (CODE_LEX_Cour){
            case SUP_TOKEN:
                Test_Symbole(CODES_LEX.SUP_TOKEN,ERR_SYNTAX.SUP_ERR);
                ADDITIVE_EXPRESSION();
                RELATIONAL_EXPRESSION2();
                break;
            case SUPEG_TOKEN:
                Test_Symbole(CODES_LEX.SUPEG_TOKEN,ERR_SYNTAX.SUPEG_ERR);
                ADDITIVE_EXPRESSION();
                RELATIONAL_EXPRESSION2();
                break;
            case INF_TOKEN:
                Test_Symbole(CODES_LEX.INF_TOKEN,ERR_SYNTAX.INF_ERR);
                ADDITIVE_EXPRESSION();
                RELATIONAL_EXPRESSION2();
                break;
            case INFEG_TOKEN:
                Test_Symbole(CODES_LEX.INFEG_TOKEN,ERR_SYNTAX.INFEG_ERR);
                ADDITIVE_EXPRESSION();
                RELATIONAL_EXPRESSION2();
                break;
            case OU_TOKEN:
            case ET_TOKEN:
            case PF_TOKEN:
            case PV_TOKEN:
            case DIFF_TOKEN:
            case EG_TOKEN:
                break;
            default:
                ERROR(courant, ERR_SYNTAX.RELATIONAL_EXPRESSION2_ERR);
                break;
        }

    }

    @Override
    public void ADDITIVE_EXPRESSION() throws Exception{
     if(CODE_LEX_Cour==CODES_LEX.ID_TOKEN ||
             CODE_LEX_Cour==CODES_LEX.NUM_TOKEN || CODE_LEX_Cour==CODES_LEX.PO_TOKEN
     || CODE_LEX_Cour == CODES_LEX.CAR_CONSTANTE || CODE_LEX_Cour == CODES_LEX.CHAINE_CONSTANTE

     ){
     MULTIPLICATIVE_EXPRESSION();
     ADDITIVE_EXPRESSION2();
     }else{
      ERROR(courant, ERR_SYNTAX.ADDITIVE_EXPRESSION_ERR);
     }
    }

    @Override
    public void ADDITIVE_EXPRESSION2()throws Exception{
        switch (CODE_LEX_Cour){
            case PLUS_TOKEN:
                Test_Symbole(CODES_LEX.PLUS_TOKEN,ERR_SYNTAX.PLUS_ERR);
                MULTIPLICATIVE_EXPRESSION();
                ADDITIVE_EXPRESSION2();
                break;
            case MOINS_TOKEN:
                MULTIPLICATIVE_EXPRESSION();
                ADDITIVE_EXPRESSION2();
                break;
            case OU_TOKEN:
            case ET_TOKEN:
            case PF_TOKEN:
            case PV_TOKEN:
            case DIFF_TOKEN:
            case EG_TOKEN:
            case SUP_TOKEN:
            case SUPEG_TOKEN:
            case INF_TOKEN:
            case INFEG_TOKEN:
            case CRF_TOKEN:
                break;
            default:
                ERROR(courant, ERR_SYNTAX.ADDITIVE_EXPRESSION2_ERR);
                break;
        }

    }

    @Override
    public void MULTIPLICATIVE_EXPRESSION() throws Exception{
        if(CODE_LEX_Cour==CODES_LEX.ID_TOKEN ||
                CODE_LEX_Cour==CODES_LEX.NUM_TOKEN || CODE_LEX_Cour==CODES_LEX.PO_TOKEN
                || CODE_LEX_Cour == CODES_LEX.CAR_CONSTANTE || CODE_LEX_Cour == CODES_LEX.CHAINE_CONSTANTE

        )  {
            POSTFIX_EXPRESSION();
      MULTIPLICATIVE_EXPRESSION2();
     }else{
      ERROR(courant, ERR_SYNTAX.MULTIPLICATIVE_EXPRESSION_ERR);
     }
    }

    @Override
    public void MULTIPLICATIVE_EXPRESSION2() throws Exception{
        switch (CODE_LEX_Cour){
            case MULT_TOKEN:
                Test_Symbole(CODES_LEX.MULT_TOKEN,ERR_SYNTAX.MULT_ERR);
                POSTFIX_EXPRESSION();
                MULTIPLICATIVE_EXPRESSION2();
                break;
            case MOD_TOKEN:
                Test_Symbole(CODES_LEX.MOD_TOKEN,ERR_SYNTAX.MOD_ERR);
                POSTFIX_EXPRESSION();
                MULTIPLICATIVE_EXPRESSION2();
                break;
            case DIV_TOKEN:
                Test_Symbole(CODES_LEX.DIV_TOKEN,ERR_SYNTAX.DIV_ERR);
                POSTFIX_EXPRESSION();
                MULTIPLICATIVE_EXPRESSION2();
                break;
            case OU_TOKEN:
            case ET_TOKEN:
            case PF_TOKEN:
            case PV_TOKEN:
            case DIFF_TOKEN:
            case EG_TOKEN:
            case SUP_TOKEN:
            case SUPEG_TOKEN:
            case INF_TOKEN:
            case INFEG_TOKEN:
            case CRF_TOKEN:
            case PLUS_TOKEN:
            case MOINS_TOKEN:
                break;
            default:
                ERROR(courant, ERR_SYNTAX.MULTIPLICATIVE_EXPRESSION2_ERR);
                break;
        }

    }

    @Override
    public void POSTFIX_EXPRESSION() throws Exception{
     if(CODE_LEX_Cour==CODES_LEX.ID_TOKEN){
      Test_Symbole(CODES_LEX.ID_TOKEN,ERR_SYNTAX.ID_ERR);
      POSTFIX_EXPRESSION2();
     }else if(CODE_LEX_Cour==CODES_LEX.NUM_TOKEN){
      Test_Symbole(CODES_LEX.NUM_TOKEN,ERR_SYNTAX.NUM_ERR);
     }else if(CODES_LEX.PO_TOKEN == CODE_LEX_Cour){
      Test_Symbole(CODES_LEX.PO_TOKEN,ERR_SYNTAX.PO_ERR);
      TEST_EXPRESSION();
      Test_Symbole(CODES_LEX.PF_TOKEN,ERR_SYNTAX.PF_ERR);
     }else if(CODE_LEX_Cour==CODES_LEX.CAR_CONSTANTE){
         Test_Symbole(CODES_LEX.CAR_CONSTANTE,ERR_SYNTAX.CAR_CONSTANTE_ERR);
     }else if(CODE_LEX_Cour==CODES_LEX.CHAINE_CONSTANTE){
         Test_Symbole(CODES_LEX.CHAINE_CONSTANTE,ERR_SYNTAX.CHAINE_CONSTANTE_ERR);
     }
     else{
      ERROR(courant, ERR_SYNTAX.POSTFIX_EXPRESSION_ERR);
     }
    }

    @Override
    public void POSTFIX_EXPRESSION2() throws Exception{
        switch (CODE_LEX_Cour){
            case PO_TOKEN:
            case CRO_TOKEN:
                IDENTIFIER2();
                break;
            case OU_TOKEN:
            case ET_TOKEN:
            case PF_TOKEN:
            case PV_TOKEN:
            case DIFF_TOKEN:
            case EG_TOKEN:
            case SUP_TOKEN:
            case SUPEG_TOKEN:
            case INF_TOKEN:
            case INFEG_TOKEN:
            case CRF_TOKEN:
            case PLUS_TOKEN:
            case MOINS_TOKEN:
            case MULT_TOKEN:
            case MOD_TOKEN:
            case DIV_TOKEN:
                break;
            default:
                ERROR(courant, ERR_SYNTAX.POSTFIX_EXPRESSION2_ERR);
                break;
        }
    }

    @Override
    public void IDENTIFIER2() throws Exception{
      if (CODE_LEX_Cour==CODES_LEX.CRO_TOKEN){
       TAB_DIM_MULT();
      }else if (CODE_LEX_Cour==CODES_LEX.PO_TOKEN){
       Test_Symbole(CODES_LEX.PO_TOKEN,ERR_SYNTAX.PO_ERR);
       ARGUMENT_LIST();
       Test_Symbole(CODES_LEX.PF_TOKEN,ERR_SYNTAX.PF_ERR);
      }else{
       ERROR(courant, ERR_SYNTAX.IDENTIFIER2_ERR);
      }
    }

    @Override
    public void TAB_DIM_MULT() throws Exception{
        if(CODE_LEX_Cour==CODES_LEX.CRO_TOKEN){
            Test_Symbole(CODES_LEX.CRO_TOKEN,ERR_SYNTAX.CRO_ERR);
            ADDITIVE_EXPRESSION();
            Test_Symbole(CODES_LEX.CRF_TOKEN,ERR_SYNTAX.CRF_ERR);
            TAB_FACTOR();
        }else{
            ERROR(courant,ERR_SYNTAX.TAB_DIM_MULT_ERR);
        }

    }

    @Override
    public void TAB_FACTOR() throws Exception{
        switch (CODE_LEX_Cour){
            case CRO_TOKEN:
                TAB_DIM_MULT();
                break;
            case OU_TOKEN:
            case ET_TOKEN:
            case PF_TOKEN:
            case PV_TOKEN:
            case DIFF_TOKEN:
            case EG_TOKEN:
            case SUP_TOKEN:
            case SUPEG_TOKEN:
            case INF_TOKEN:
            case INFEG_TOKEN:
            case CRF_TOKEN:
            case PLUS_TOKEN:
            case MOINS_TOKEN:
            case MULT_TOKEN:
            case MOD_TOKEN:
            case DIV_TOKEN:
                break;
            default:
                ERROR(courant, ERR_SYNTAX.TAB_FACTOR_ERR);
                break;

        }
    }

    @Override
    public void RETURN_STATEMENT() throws Exception{
        if(CODE_LEX_Cour==CODES_LEX.RETOURNER_TOKEN){
            Test_Symbole(CODES_LEX.RETOURNER_TOKEN,ERR_SYNTAX.RETOURNER_ERR);
            VAL_RETOURNER();
            Test_Symbole(CODES_LEX.PV_TOKEN,ERR_SYNTAX.PV_ERR);
        }else{
            ERROR(courant,ERR_SYNTAX.RETURN_STATEMENT_ERR);
        }

    }

    @Override
    public void VAL_RETOURNER() throws Exception{
     if(CODE_LEX_Cour==CODES_LEX.ID_TOKEN || CODE_LEX_Cour==CODES_LEX.NUM_TOKEN || CODE_LEX_Cour==CODES_LEX.PO_TOKEN || CODE_LEX_Cour==CODES_LEX.NON_TOKEN){
     TEST_EXPRESSION();
     }else{
      ERROR(courant, ERR_SYNTAX.VAL_RETOURNER_ERR);
     }
    }

    @Override
    public void TYPE_SPECIFIER() throws Exception{
      if(CODES_LEX.ENTIER_TOKEN==CODE_LEX_Cour){
       Test_Symbole(CODES_LEX.ENTIER_TOKEN,ERR_SYNTAX.ENTIER_ERR);
      }else if(CODE_LEX_Cour==CODES_LEX.CAR_TOEKN){
       Test_Symbole(CODES_LEX.CAR_TOEKN,ERR_SYNTAX.CAR_ERR);
      }else if(CODE_LEX_Cour==CODES_LEX.REEL_TOKEN){
       Test_Symbole(CODES_LEX.REEL_TOKEN,ERR_SYNTAX.REEL_ERR);
      }else if(CODES_LEX.VIDE_TOKEN==CODE_LEX_Cour){
       Test_Symbole(CODES_LEX.VIDE_TOKEN,ERR_SYNTAX.VIDE_ERR);
      }else{
       ERROR(courant, ERR_SYNTAX.TYPE_SPECIFIER_ERR);
      }
    }
}
