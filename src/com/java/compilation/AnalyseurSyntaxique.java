package com.java.compilation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class AnalyseurSyntaxique implements SyntaxInterface{
   private   ArrayList<CODES_LEX> listLexical = new ArrayList<>();
   private CODES_LEX CODE_LEX_Cour;
   private int index = 0;
    AnalyseurSyntaxique() throws Exception{
     analyseurLexical();
    }
    void analyseurLexical() throws Exception{
     File file = new File("C:\\Users\\Dell\\Desktop\\code\\code.fc");
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
     while (al.getCour()<charList.size()-1){
      TSym_Cour sym_cour = al.sym_suivant();
      listLexical.add( sym_cour.CODE);
      al.Lire_Car();
      while((al.Car_Cour==' ' || al.Car_Cour=='\n' || al.Car_Cour=='\t') && al.getCour()<charList.size()) {
       al.Lire_Car();
      }
     }
     fr.close();

    }

/*
    public void display(){

     for (CODES_LEX CODE:this.listLexical) {
      System.out.println(CODE);
     }
    }

 */


    public CODES_LEX Sym_Suiv() throws  Exception{
      CODE_LEX_Cour = listLexical.get(index++);
       return CODE_LEX_Cour;
    }

    void Test_Symbole(CODES_LEX cl,ERR_SYNTAX ERR) throws Exception{
      if(cl == CODE_LEX_Cour){
       Sym_Suiv();
      }else{
       ERROR(ERR);
      }
    }

    public void ERROR(ERR_SYNTAX ERR){
     // code Here

    }

    public  boolean analyseSyntax(){
        return true;
    }



    @Override
    public void PROGRAM() {

    }

    @Override
    public void PROGRAM2() {

    }

    @Override
    public void EXTERNAL_DECLARATION() {

    }

    @Override
    public void FUNCTION_DEFINITION() {

    }

    @Override
    public void PARAMETER_DECLARATION() {

    }

    @Override
    public void PLUS_PARAMETRES() {

    }

    @Override
    public void DECLARATION() {

    }

    @Override
    public void DECLARATION2() {

    }

    @Override
    public void VALUE() {

    }

    @Override
    public void APPEL_FONCTION() {

    }

    @Override
    public void ARGUMENT_LIST() {

    }

    @Override
    public void VALUE_EPSILON() {

    }

    @Override
    public void COMPOUND_STATEMENT() {

    }

    @Override
    public void STATEMENT() {

    }

    @Override
    public void EXPRESSION_STATEMENT() {

    }

    @Override
    public void EXPRESSION_STATEMENT2() {

    }

    @Override
    public void CONDITIONAL_STATEMENT() {

    }

    @Override
    public void SI_STATEMENT() {

    }

    @Override
    public void SINONSTATEMENT() {

    }

    @Override
    public void SELON_STATEMENT() {

    }

    @Override
    public void SELON_FACTORI() {

    }

    @Override
    public void CASE() {

    }

    @Override
    public void LOOP_STATEMENT() {

    }

    @Override
    public void TANQUE_STATEMENT() {

    }

    @Override
    public void POUR_STATEMENT() {

    }

    @Override
    public void REPETER_STATEMENT() {

    }

    @Override
    public void INITIALISATION_EXPRESSION() {

    }

    @Override
    public void AUTRE_INITIALISATIONS() {

    }

    @Override
    public void AFFECTATION_EXPRESSION() {

    }

    @Override
    public void AFFECTATION_EXPRESSION2() {

    }

    @Override
    public void TEST_EXPRESSION() {

    }

    @Override
    public void LOGICAL_OU_EXPRESSION() {

    }

    @Override
    public void LOGICAL_OU_EXPRESSION2() {

    }

    @Override
    public void EQUALITY_EXPRESSION() {

    }

    @Override
    public void EQUALITY_EXPRESSION2() {

    }

    @Override
    public void RELATIONAL_EXPRESSION() {

    }

    @Override
    public void RELATIONAL_EXPRESSION2() {

    }

    @Override
    public void ADDITIVE_EXPRESSION() {

    }

    @Override
    public void ADDITIVE_EXPRESSION2() {

    }

    @Override
    public void MULTIPLICATIVE_EXPRESSION() {

    }

    @Override
    public void MULTIPLICATIVE_EXPRESSION2() {

    }

    @Override
    public void POSTFIX_EXPRESSION() {

    }

    @Override
    public void POSTFIX_EXPRESSION2() {

    }

    @Override
    public void IDENTIFIER2() {

    }

    @Override
    public void TAB_DIM_MULT() {

    }

    @Override
    public void TAB_FACTOR() {

    }

    @Override
    public void RETURN_STATEMENT() {

    }

    @Override
    public void VAL_RETOURNER() {

    }

    @Override
    public void TYPE_SPECIFIER() {

    }
}
