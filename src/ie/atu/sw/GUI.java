package ie.atu.sw;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author James Murray
 * @version 1.0
 * @since 19.0.1
 *
 * A GUI using JSwing to display the options.
 * This class holds the form that takes input from the user.
 * Based on Cave of Programming but edited to add file chooser buttons. I removed a lot
 * from his layout as well, and the form event was edited to fit my program.
 */
public class GUI extends JPanel{
        JPanel panel = new JPanel();
        private final JLabel commonWordsLabel;
        private final JTextField commonWordsField;
        private final JLabel dictLabel;
        private final JTextField dictField;
        private final JLabel textFileLabel;
        private final JTextField textFileField;
        private final JLabel outputFileLabel;
        private final JTextField outputFileField;
        private FormListener formListener;
        private final JButton button1;
        private final JButton button2;
        private final JButton button3;
        private final JButton button4;
        private final JButton button5;

    public GUI() {

            commonWordsLabel = new JLabel("Enter Common words");
            commonWordsField = new JTextField(10);
            button1 = new JButton("Browse");

            dictLabel = new JLabel("Enter dictionary");
            dictField = new JTextField(10);
            button2 = new JButton("Browse");

            textFileLabel = new JLabel("Enter text");
            textFileField = new JTextField(10);
            button3 = new JButton("Browse");

            outputFileLabel = new JLabel("Enter output file");
            outputFileField = new JTextField(10);
            button4 = new JButton("Browse");

            button5 = new JButton("OK");

            // Create a file chooser
            JFileChooser fileChooser = new JFileChooser();

            // Create a button to browse files for each field
            button1.addActionListener(e -> {
                int result = fileChooser.showOpenDialog(panel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    commonWordsField.setText(file.getName());
                }
            });


            button2.addActionListener(e -> {
                int result = fileChooser.showOpenDialog(panel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    dictField.setText(file.getName());
                }
            });

            button3.addActionListener(e -> {
                int result = fileChooser.showOpenDialog(panel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    textFileField.setText(file.getAbsolutePath());
                }
            });

            button4.addActionListener(e -> {
                int result = fileChooser.showOpenDialog(panel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    outputFileField.setText(file.getName());
                }
            });

            button5.addActionListener(e -> {
                String commonWords = commonWordsField.getText();
                String dict = dictField.getText();
                String textFile = textFileField.getText();
                String outputFile = outputFileField.getText();

                FormEvent ev;
                try {
                    ev = new FormEvent(this,outputFile, textFile, dict, commonWords);
                    if(formListener != null){
                        formListener.formEventOccurred(ev);
                    }
                } catch (Exception ex) {
                    handleException();
                }


            });

            panel.add(button5);


            Border innerBorder = BorderFactory.createTitledBorder("Index Text");
            Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);

            setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));
            layoutComponents();

        }
    //O(n) Worst case is it has to run through all the elements in the map
    private void handleException() {
        StringBuilder messageBuilder = new StringBuilder("The following field(s) must be entered to continue:\n");
        Map<String, Object> variables = new HashMap<>();
        variables.put("Text File", textFileField.getText());
        variables.put("Dictionary", dictField.getText());
        variables.put("Common Words File", commonWordsField.getText());
        variables.put("Output File", outputFileField.getText());

        for (Map.Entry<String, Object> entry : variables.entrySet()) {System.out.println(entry.getValue().toString());
            if ("".equals(entry.getValue().toString())) {
                messageBuilder.append(entry.getKey()).append("\n");
            }
        }
        String message = messageBuilder.toString();
        JOptionPane.showMessageDialog(null, message, "Alert", JOptionPane.WARNING_MESSAGE);
    }
    //O(1) Constant time operations. No loops.
    private void layoutComponents(){

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        /////////////////first row/////////////////////////////

        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 0.1;



        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(commonWordsLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(commonWordsField, gc);

        gc.gridx = 2;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        add(button1, gc);

        //////////////////////////Next row /////////////////////////
        gc.gridy++;

        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(dictLabel, gc);

        gc.gridx = 1;

        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(dictField,gc);

        gc.gridx = 2;

        gc.anchor = GridBagConstraints.LINE_START;
        add(button2, gc);

        //////////////////////////Next row /////////////////////////
        gc.gridy++;

        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.insets = new Insets(0,0,0,5);
        gc.anchor = GridBagConstraints.LINE_END;
        add(textFileLabel, gc);

        gc.gridx = 1;

        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(textFileField,gc);

        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        add(button3, gc);

        //////////////////////////Next row /////////////////////////
        gc.gridy++;

        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.insets = new Insets(0,0,0,5);
        gc.anchor = GridBagConstraints.LINE_END;
        add(outputFileLabel, gc);

        gc.gridx = 1;

        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(outputFileField,gc);

        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        add(button4, gc);


        ///////////////// Next row
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 2.0;


        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(button5, gc);
    }

    /**
     * Allows the GUI to update the TextPanel without them being directly
     * related
     * @param formListener an object of the FormListener class
     */
    //O(1) Just set the form listener
    public void setFormListener(FormListener formListener) {
            this.formListener = formListener;
    }



}
