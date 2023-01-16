package ie.atu.sw;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author James Murray
 * @version 1.0
 * @since 19.0.1
 *
 * A central hub in the GUI that facilitates communication between teh various
 * components.
 */
public class MainFrame extends JFrame {
    private final TextPanel textPanel;

    public MainFrame(){
        super("Text Indexer");

        setLayout(new BorderLayout());
        textPanel = new TextPanel();
        GUI gui = new GUI();


        gui.setFormListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (ConcurrentSkipListMap.Entry<String, WordDetail> entry : e.getWordIndex().entrySet()) {
                sb.append("DEFINITIONS: \n").append(entry.getKey()).append(" : ")
                        .append(entry.getValue().getDefinition()).append("\n").append("PAGES: \n")
                        .append(entry.getValue().getPages()).append("\n").append(System.getProperty("line.separator"));
            }
            textPanel.appendText(sb.toString());});

        add(gui,BorderLayout.WEST);
        add(textPanel, BorderLayout.CENTER);


        setSize(800,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
}
