package ie.atu.sw;

import javax.swing.*;
import java.awt.*;

/**
 * @author James Murray
 * @version 1.0
 * @since 19.0.1
 *
 * This class displays the output if the user is using the GUI.
 * Taken from Cave of Programming
 */
public class TextPanel extends JPanel {
    private final JTextArea textArea;

    public TextPanel(){
        textArea = new JTextArea();
        setLayout(new BorderLayout());

        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    /**
     * Appends text to the text area
     * @param text the text to be added
     */
    //O(1) constant time operation
    public void appendText(String text){

        textArea.append(text);

    }

}
