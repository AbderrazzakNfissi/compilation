import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Editor extends JFrame {
    File selectedFile;
    public  static  JTextArea codeTextArea;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel( new NimbusLookAndFeel() );
        }catch (Exception e){
            System.out.println("ee");
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Editor frame = new Editor();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    KeywordHighlighter window = new KeywordHighlighter(codeTextArea);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Editor() {
        setTitle("Fcode");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 700);


        //Menu
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnNewMenu = new JMenu("File");
        mnNewMenu.setMnemonic( 'F' );
        menuBar.add(mnNewMenu);

        JMenuItem openMenuItem = new JMenuItem("Open");
        openMenuItem.setMnemonic('o');
        mnNewMenu.add(openMenuItem);

        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setMnemonic('S');
        mnNewMenu.add(saveItem);


        //CodeTextArea content
        codeTextArea = new JTextArea(30,50);
        saveItem.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String content = codeTextArea.getText();
                    FileWriter fw = new FileWriter(selectedFile);
                    fw.write(content);
                    fw.close();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                }
        });
        //When Open button Clicked
        openMenuItem.addActionListener((ActionListener) new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("FCode Files", "fc");
                fileChooser.setFileFilter(filter);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                     selectedFile = fileChooser.getSelectedFile();
                    try {
                        String fileContent = new String(Files.readAllBytes(selectedFile.toPath()));
                        codeTextArea.setText(fileContent);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }

        });

        //General Content Panel
        contentPane = new JPanel();

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); //create space between the edges of the container and the components inside it
        setContentPane(contentPane);
        //To add absolute layout
        contentPane.setLayout(null);

        //Code ScrollPane
        JScrollPane codeScrollPane = new JScrollPane(codeTextArea);
        codeScrollPane.setBounds(95, 10, 423, 563);
        codeScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        codeScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        contentPane.add(codeScrollPane);

        JButton lexicalButton = new JButton("Analyseur Lexical >> ");
        lexicalButton.setBounds(523, 271, 179, 41);
        lexicalButton.setIcon(new ImageIcon("C:\\Users\\AyouByte\\Downloads\\gear (1).png"));
        contentPane.add(lexicalButton);

        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/html");

        JTextArea resultTextArea = new JTextArea(30,50);
        resultTextArea.setEditable(false);

        JScrollPane resultScrollPane = new JScrollPane(editorPane);
        resultScrollPane.setBounds(712, 10, 423, 563);
        resultScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        resultScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        contentPane.add(resultScrollPane);


        // AFTER
        JButton syntaxButton = new JButton("Analyseur syntaxique >>");
        syntaxButton.setBounds(523, 335, 179, 41);
        contentPane.add(syntaxButton);
        JButton semanticButton = new JButton("Analyseur sémantique >>");
        semanticButton.setBounds(523, 397, 179, 41);
        contentPane.add(semanticButton);


        // AFTER

        lexicalButton.addActionListener(

                (ActionListener) new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String code = codeTextArea.getText();
                        //The idea here, is get The content of codeTextArea store it in a string variable, then append
                        //this string to this file
                        //The file will be created automatically in the project
                        if(selectedFile==null){
                            JOptionPane.showMessageDialog(null, "Erreur : Aucun Fichier seléctioné", "Aucun fichier sélectionné ", JOptionPane.ERROR_MESSAGE);

                        }else{
                            File fileACompiler = selectedFile;
                            FileWriter fw;
                            try {
                                fw = new FileWriter(fileACompiler);
                                fw.write(code);
                                fw.close();

                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }


                            //Here we redirected the console output to the output variable, then we set resultTextArea
                            //by the content of the output variable

                            try {

                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                PrintStream ps = new PrintStream(baos);
                                PrintStream old = System.out;
                                System.setOut(ps);

                                //main(String[] args, File f) method main in the Main_ class , we added one argument
                                //to pass the file
                                Main.main(null, fileACompiler);

                                System.out.flush();
                                System.setOut(old);
                                String output = baos.toString();
                                resultTextArea.setBackground(Color.WHITE);
                                resultTextArea.setForeground(Color.BLACK);
                                editorPane.setText(output);
                            } catch (Exception e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }

                        }


                    }

                }


        );


    }
}
