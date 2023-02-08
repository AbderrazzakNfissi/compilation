package com.java.compilation;

import java.util.ArrayList;
import java.util.List;

public class AnalyseurLexical{
    private int cour;
    private TSym_Cour sym_cour;
    public  List<Character> charList;
    public char Car_Cour;
    public ArrayList<TSym_Cour> listLexical;
    AnalyseurLexical(List<Character> charList){
        listLexical = new ArrayList<TSym_Cour>();
        this.charList = charList;
        cour = 0;
        sym_cour = new TSym_Cour();
    }

    public int getCour() {
        return cour;
    }

    public void Lire_Car() {
        if(cour<charList.size()){
            Car_Cour = charList.get(cour);
            cour++;
        }
    }



    public boolean isSpaces(char car){
        return (car=='\n' || car==' ' || car == '\t');
    }

    public TSym_Cour Lire_chaine_constante(){
        String symbole = "\"";
        Lire_Car();
        while(!Character.toString(Car_Cour).equals("\"")){
            if(cour==charList.size()-1 && !Character.toString(Car_Cour).equals("\"")){
              sym_cour.setNom("unclosed string");
              sym_cour.setCODE(CODES_LEX.UNCLOSED_CHAINE);
              return sym_cour;
            }
            symbole += (char) Car_Cour;
            Lire_Car();
        }
        symbole+=((char) Car_Cour);
        sym_cour.setNom(symbole);
        sym_cour.setCODE(CODES_LEX.CHAINE_CONSTANTE);
        return  sym_cour;
    }
    public TSym_Cour lire_car(){
        String symbole = "\'";
        Lire_Car();
        symbole += ((char) Car_Cour);
        Lire_Car();
        if(Car_Cour!='\''){
            while(Car_Cour!='\''){
                symbole+=Car_Cour;
                Lire_Car();
            }
            symbole+=Car_Cour;
            sym_cour.setCODE(CODES_LEX.ERREUR_TOKEN);
            sym_cour.setNom(symbole);
            listLexical.add(sym_cour);
            return sym_cour;
        }else{
            symbole+=((char) Car_Cour);
        }
        sym_cour.setNom(symbole);
        sym_cour.setCODE(CODES_LEX.CAR_CONSTANTE);

        return  sym_cour;
    }

    public TSym_Cour sym_suivant(){
        if(Character.toString(Car_Cour).equals("\'")){
            lire_car();
        }else
        if(Character.toString(Car_Cour).equals("\"")){
            Lire_chaine_constante();
        }else
        if(Character.isLetter(Car_Cour)){
            this.sym_cour = lire_mot();
            if(cour!=charList.size()-1){
                Car_Cour = charList.get(cour-1);
                cour--;
            }

        }else if(Character.isDigit(Car_Cour)){
            this.sym_cour = lire_nombre();
            if(cour!=charList.size()-1){
                Car_Cour = charList.get(cour-1);
                cour--;
            }
        }else if(!isSpaces(Car_Cour)){
            this.sym_cour = lireSpecial();
        }

        return sym_cour;
    }

    public TSym_Cour lire_nombre(){
        String symbole="";
        boolean est_point = false;

        while(Character.isDigit(Car_Cour) || Car_Cour=='.'){
            if(!est_point && Car_Cour=='.'){
                symbole += (char) Car_Cour;
                Lire_Car();
                est_point = true;
            }else{
                symbole += (char) Car_Cour;
                Lire_Car();
            }
        }
        sym_cour.setNom(symbole);
        sym_cour.setCODE(CODES_LEX.NUM_TOKEN);

        return sym_cour;
    }


    public TSym_Cour lire_mot(){
        String symbole = "";
        while(Character.isDigit(Car_Cour) || Character.isLetter(Car_Cour)){
            symbole+=Car_Cour;
            Lire_Car();
        }
        this.sym_cour.setNom(symbole);
        if(symbole.equals("fonction")){
            sym_cour.setCODE(CODES_LEX.FONCTION_TOKEN);
        }else if(symbole.equals("principale")){
            sym_cour.setCODE(CODES_LEX.PRINCIPALE_TOKEN);
        }else if(symbole.equals("entier")){
            sym_cour.setCODE(CODES_LEX.ENTIER_TOKEN);
        }else if(symbole.equals("debut")){
            sym_cour.setCODE(CODES_LEX.DEBUT_TOKEN);
        }else if(symbole.equals("fin")){
            sym_cour.setCODE(CODES_LEX.FIN_TOKEN);
        }else if(symbole.equals("retourner")){
            sym_cour.setCODE(CODES_LEX.RETOURNER_TOKEN);
        }else if(symbole.equals("afficher")){
            sym_cour.setCODE(CODES_LEX.AFFICHER_TOKEN);
        }else if(symbole.equals("lire")){
            sym_cour.setCODE(CODES_LEX.LIRE_TOKEN);
        }else if(symbole.equals("car")){
            sym_cour.setCODE(CODES_LEX.CAR_TOEKN);
        }else if(symbole.equals("reel")){
            sym_cour.setCODE(CODES_LEX.REEL_TOKEN);
        }else if(symbole.equals("vide")){
            sym_cour.setCODE(CODES_LEX.VIDE_TOKEN);
        }else if(symbole.equals("si")){
            sym_cour.setCODE(CODES_LEX.SI_TOKEN);
        }else if(symbole.equals("alors")){
            sym_cour.setCODE(CODES_LEX.ALORS_TOKEN);
        }else if(symbole.equals("sinon")){
            sym_cour.setCODE(CODES_LEX.SINON_TOKEN);
        }else if(symbole.equals("finsi")){
            sym_cour.setCODE(CODES_LEX.FINSI_TOKEN);
        }else if(symbole.equals("selon")){
            sym_cour.setCODE(CODES_LEX.SELON_TOKEN);
        }else if(symbole.equals("faire")){
            sym_cour.setCODE(CODES_LEX.FAIRE_TOKEN);
        }else if(symbole.equals("finselon")){
            sym_cour.setCODE(CODES_LEX.FINSELON_TOKEN);
        }else if(symbole.equals("tantque")){
            sym_cour.setCODE(CODES_LEX.TANT_QUE_TOKEN);
        }else if(symbole.equals("fintq")){
            sym_cour.setCODE(CODES_LEX.FINTQ_TOKEN);
        }else if(symbole.equals("non")){
            sym_cour.setCODE(CODES_LEX.NON_TOKEN);
        }else if(symbole.equals("repeter")){
            sym_cour.setCODE(CODES_LEX.REPETER_TOKEN);
        }else if(symbole.equals("jusqua")){
            sym_cour.setCODE(CODES_LEX.JUSQUA_TOKEN);
        }else if(symbole.equals("pour")){
            sym_cour.setCODE(CODES_LEX.POUR_TOKEN);
        }else if(symbole.equals("finpour")){
            sym_cour.setCODE(CODES_LEX.FINPOUR_TOKEN);
        }else if (symbole.equals("sortir")){
            sym_cour.setCODE(CODES_LEX.SORTIR_TOKEN);
        }else if(symbole.equals("continuer")){
            sym_cour.setCODE(CODES_LEX.CONTINUER_TOKEN);
        }else if(symbole.equals("deftype")){
            sym_cour.setCODE(CODES_LEX.DEFTYPE_TOKEN);
        }else if(symbole.equals("structure")){
            sym_cour.setCODE(CODES_LEX.STRUCTURE_TOKEN);
        }else if(symbole.equals("finstruct")){
            sym_cour.setCODE(CODES_LEX.FINSTRUCT_TOKEN);
        }else if(symbole.equals("fincas")){
            sym_cour.setCODE(CODES_LEX.FINSTRUCT_TOKEN);
        }else if(symbole.equals("ou")){
            sym_cour.setCODE(CODES_LEX.OU_TOKEN);
        }
        else if(symbole.equals("mod")){
            sym_cour.setCODE(CODES_LEX.MOD_TOKEN);
        }else{
            sym_cour.setCODE(CODES_LEX.ID_TOKEN);
        }

        return  this.sym_cour;
    }

    public TSym_Cour lireSpecial(){
        String symbole = "";

        switch (Car_Cour){
            case '"' : {
                sym_cour.nom = "\"";
                sym_cour.setCODE(CODES_LEX.QUOTE_TOKEN);
            }
            break;
            case '(': {
                sym_cour.nom = "(";
                sym_cour.setCODE(CODES_LEX.PO_TOKEN);

            }
            break;
            case ')': {
                sym_cour.nom = ")";
                sym_cour.setCODE(CODES_LEX.PF_TOKEN);
            }
            break;
            case '{': {
                sym_cour.nom = "{";
                sym_cour.setCODE(CODES_LEX.ACO_TOKEN);

            }
            break;
            case '}': {
                sym_cour.nom = "}";
                sym_cour.setCODE(CODES_LEX.ACF_TOKEN);

            }
            break;
            case '[': {
                sym_cour.nom = "[";
                sym_cour.setCODE(CODES_LEX.CRO_TOKEN);

            }
            break;
            case ']': {
                sym_cour.nom = "]";
                sym_cour.setCODE(CODES_LEX.CRF_TOKEN);

            }
            break;
            case '=': {
                sym_cour.nom = "=";
                sym_cour.setCODE(CODES_LEX.EG_TOKEN);

            }
            break;
            case '+': {
                sym_cour.nom = "+";
                sym_cour.setCODE(CODES_LEX.PLUS_TOKEN);

            }
            break;
            case ';': {
                sym_cour.nom = ";";
                sym_cour.setCODE(CODES_LEX.PV_TOKEN);

            }
            break;
            case '.': {
                sym_cour.nom = ".";
                sym_cour.setCODE(CODES_LEX.PT_TOKEN);

            }
            break;
            case ',': {
                sym_cour.nom = ",";
                sym_cour.setCODE(CODES_LEX.VIR_TOKEN);

            }
            break;
            case '-': {
                sym_cour.nom = "-";
                sym_cour.setCODE(CODES_LEX.MOINS_TOKEN);

            }
            break;
            case '*': {
                sym_cour.nom = "*";
                sym_cour.setCODE(CODES_LEX.MULT_TOKEN);

            }
            break;
            case '/': {

                symbole+= (char) Car_Cour;
                Lire_Car();
                if (Car_Cour == '*') {
                    Lire_Car();
                    while ((Car_Cour != '*') && (Car_Cour != (char) -1)) {
                        symbole+= (char) Car_Cour;
                        Lire_Car();
                    }
                    if (Car_Cour != '*') {
                        Lire_Car();
                        sym_cour.setCODE(CODES_LEX.ERREUR_TOKEN);
                    } else
                        Lire_Car();
                    if (Car_Cour == '/') {
                        sym_cour.nom = symbole.toString();
                        sym_cour.setCODE(CODES_LEX.COMMENT_TOKEN);
                    }
                } else {
                    sym_cour.nom = symbole.toString();
                    sym_cour.setCODE(CODES_LEX.DIV_TOKEN);
                }
            }break;
            case '>' :
                Lire_Car();
                if(Car_Cour=='=') {
                    sym_cour.nom = ">=";
                    sym_cour.setCODE(CODES_LEX.SUPEG_TOKEN);
                } else {
                    sym_cour.nom = symbole.toString();
                    sym_cour.setCODE(CODES_LEX.SUP_TOKEN);

                    Car_Cour = charList.get(cour-1);
                    cour--;
                }
                break;
            case '<' :
                Lire_Car();
                if(Car_Cour=='=') {
                    sym_cour.nom = "<=";
                    sym_cour.setCODE(CODES_LEX.INFEG_TOKEN);
                }else
                if(Car_Cour=='>') {
                    sym_cour.nom = "<>";
                    sym_cour.setCODE(CODES_LEX.DIFF_TOKEN);
                } else {
                    sym_cour.nom = "<";
                    sym_cour.setCODE(CODES_LEX.INF_TOKEN);

                    Car_Cour = charList.get(cour-1);
                    cour--;
                }
                break;
            case ':' :

                Lire_Car();
                if(Car_Cour=='=') {
                    sym_cour.nom = ":=";
                    sym_cour.setCODE(CODES_LEX.AFF_TOKEN);
                } else {
                    sym_cour.setNom(":");
                    sym_cour.setCODE(CODES_LEX.DEUX_POINTS_TOKEN);

                    Car_Cour = charList.get(cour-1);
                    cour--;
                }
                break;
            default:
                sym_cour.setNom(Character.toString(Car_Cour));
                sym_cour.setCODE(CODES_LEX.ERREUR_TOKEN);
        }


        return this.sym_cour;
    }


}
