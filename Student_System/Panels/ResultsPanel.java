package Student_System.Panels;

//Name:Tharik Parvas S/O Checkoussain
//Admission Number: p2349288
//Class: DIT/FT/2A/03

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;

public class ResultsPanel extends JPanel {
    private JTextArea resultsArea;
    private JButton exitButton;

    public ResultsPanel() {
        // Create a TitledBorder with an empty border
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Results");
        titledBorder.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // No outline

        // Set the border with no outline but keep the title
        setBorder(titledBorder);
        setLayout(new BorderLayout());
        // setBackground(Color.LIGHT_GRAY); // Adjust to match the image
        setPreferredSize(new Dimension(300, 230)); // Set button size

        // Initialize components
        resultsArea = new JTextArea();
        resultsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsArea);

        exitButton = new JButton("Exit");

        // Customize button
        exitButton.setPreferredSize(new Dimension(100, 30)); // Smaller size
        exitButton.setBackground(new Color(255, 204, 0)); // Dark yellow background
        exitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true)); // Rounded border

        exitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Thank you for using the system!");
            System.exit(0);
        });

        JPanel searchExitButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 10)); // Left alignment and
                                                                                            // padding
        searchExitButtonPanel.add(exitButton);

        // Add components to panel
        add(scrollPane, BorderLayout.CENTER);
        add(searchExitButtonPanel, BorderLayout.SOUTH);
    }

    public void displayResults(String results) {
        resultsArea.setText(results);
    }

    public void clearResults() {
        resultsArea.setText("");
    }

}
