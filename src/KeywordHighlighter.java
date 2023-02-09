
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Highlighter;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.JTextComponent;

public class KeywordHighlighter {

    public JFrame frame;
    private JTextArea textArea;

    private Highlighter h;
    private String[] keywords =  {"fonction", "entier", "debut", "fin", "retourner", "afficher",
            "lire", "car", "réel", "vide", "si", "alors", "sinon", "finsi", "selon",
            "faire", "finselon", "tantque", "fintq", "repeter", "jusqua", "pour", "finpour",
            "sortir", "continuer", "deftype", "structure", "finstruct", "mod"};


    public KeywordHighlighter(JTextArea textArea) {
        this.textArea = textArea;
        initialize();
    }

    private void initialize() {
        textArea.getDocument().addDocumentListener(new HighlightListener());
        h = textArea.getHighlighter();
    }

    class HighlightListener implements DocumentListener {
        public void changedUpdate(DocumentEvent e) {
            highlightKeywords();
        }

        public void insertUpdate(DocumentEvent e) {
            highlightKeywords();
        }

        public void removeUpdate(DocumentEvent e) {
            highlightKeywords();
        }
    }

    private void highlightKeywords() {

        Highlighter.HighlightPainter keywordPainter = new DefaultHighlighter.DefaultHighlightPainter(new Color(241, 231, 231, 134)) {
            @Override
            public void paint(Graphics g, int offs0, int offs1, Shape bounds, JTextComponent c) {
                Font font = c.getFont();
                // Make the font bold
                Font boldFont = new Font(font.getName(), Font.ITALIC, font.getSize());
                g.setFont(boldFont);
                super.paint(g, offs0, offs1, bounds, c);

            }
        };

        h.removeAllHighlights();
        String text = textArea.getText();



        for (String keyword : keywords) {
            int p0 = text.indexOf(keyword);
            while (p0 >= 0) {
                try {
                    h.addHighlight(p0, p0 + keyword.length(), keywordPainter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                p0 = text.indexOf(keyword, p0 + keyword.length());
            }
        }
    }
}
