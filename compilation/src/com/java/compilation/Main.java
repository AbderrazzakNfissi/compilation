package com.java.compilation;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main{

    public static void main(String[] args,File fileACompiler) throws Exception{
        File file = new File("C:\\Users\\Dell\\Desktop\\code\\code.fc");
        FileReader fr = new FileReader(fileACompiler);
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
        System.out.println("<table border=1 width=100% align=center>\n" +
                "  <tr>\n" +
                "    <th style='font-size:12px'>Keywords</th>\n" +
                "    <th style='font-size:12px' >Tokens</th>\n" +
                "  </tr>");

        while (al.getCour()<charList.size()-1){
            TSym_Cour sym_cour = al.sym_suivant();
            System.out.println("<tr><td style='color:#050B5F;font-size:10px;text-align:center'><b >"+sym_cour.nom+"<b></td><td>  "+sym_cour.CODE+"</td>");
            al.Lire_Car();
            while((al.Car_Cour==' ' || al.Car_Cour=='\n' || al.Car_Cour=='\t') && al.getCour()<charList.size()) {
                al.Lire_Car();
            }
        }
        fr.close();

    }
/*
    public static void main(String[] args) {
        String code="";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Dell\\Desktop\\code\\pg.codef"));

            int c = bufferedReader.read();
            int precedent = 0;
            StringBuffer buf = new StringBuffer();

            while (c != -1) {
                if ((char) c == '/') {
                    buf.setLength(0);
                    buf.append((char)c);
                    c = bufferedReader.read();
                    if ((char) c == '*') {
                        buf.append((char)c);
                        c = (char)bufferedReader.read();
                        c = check_syntax(bufferedReader, c, buf);
                        if(c == -1 ){
                            System.out.print("erreur coomm incomplet");
                            c = bufferedReader.read();
                        }

                    }else if((char) c == '/'){
                        c=bufferedReader.read();
                        while((c != '\n')){
                            c=bufferedReader.read();
                        }
                    }else {
                        code +=(char) c;
                        c = bufferedReader.read();
                    }
                }else{
                    code +=(char) c;
                    c = bufferedReader.read();
                }
            }

        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static int check_syntax(BufferedReader bufferedReader, int c, StringBuffer buf) throws IOException {
        int precedent;
        while (c != -1){
            precedent= c;
            c = bufferedReader.read();
            buf.append((char) c);
            if((char) c == '/'){
                if ((char) precedent == '*'){break;}
            }
        }
        return c;
    */
}
