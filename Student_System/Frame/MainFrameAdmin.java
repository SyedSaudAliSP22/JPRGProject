package Student_System.Frame;

//Name: Syed Saud Ali
//Admission Number: p2209111
//Class: DIT/FT/2A/03

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Student_System.Panels.InterviewPanel;
import Student_System.Panels.ModuleAdminPanel;
import Student_System.Panels.ResultsAdminPanel;
import Student_System.Panels.SearchAdminPanel;
import Student_System.Panels.StudentAdminPanel;

import java.awt.*;

public class MainFrameAdmin extends JFrame {

    public MainFrameAdmin() {
        setTitle("Student Admin System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Initialize panels
        ResultsAdminPanel resultsPanel = new ResultsAdminPanel();
        StudentAdminPanel studentPanel = new StudentAdminPanel(resultsPanel);
        ModuleAdminPanel modulePanel = new ModuleAdminPanel(studentPanel, resultsPanel);
        // make searchpanel have access to studentpanel and result panel
        SearchAdminPanel searchPanel = new SearchAdminPanel(studentPanel, resultsPanel, modulePanel);
        InterviewPanel interviewPanel = new InterviewPanel();

        // Create a main panel to hold the other panels with appropriate layout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 0.5; // Set weight for space distribution
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH; // Make components fill their cells
        // Add the panels with specific constraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(studentPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(searchPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(modulePanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(resultsPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Span the interviewPanel across both columns
        mainPanel.add(interviewPanel, gbc);

        add(mainPanel);

        // Resize Frame to fit all the size of mainPanel
        pack();

        setVisible(true);
    }
}
