package Student_System.Panels;

//Name:Tharik Parvas S/O Checkoussain
//Admission Number: p2349288
//Class: DIT/FT/2A/03

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LabelledInputPanel extends JPanel {
    private JLabel label;
    private JTextField input;

    // Constructor to initialize the panel with a label and input field
    public LabelledInputPanel(String labelText) {
        setLayout(new BorderLayout());

        // Set the preferred size of the panel
        Dimension dimension = new Dimension(200, 23);
        setPreferredSize(dimension);

        this.label = new JLabel(labelText);
        this.input = new JTextField();
        this.input.setColumns(10);

        // Add the label and text field to the panel
        add(this.label, BorderLayout.WEST);
        add(this.input, BorderLayout.EAST);
    }

    public void setText(String text) {
        this.input.setText(text);
    }

    public String getText() {
        return this.input.getText();
    }

    // set input fields editability
    public void SetEditable(boolean editable) {
        this.input.setEditable(editable);
    }
}
