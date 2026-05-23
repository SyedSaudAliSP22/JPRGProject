package Student_System.Frame;

//Name:Tharik Parvas S/O Checkoussain
//Admission Number: p2349288
//Class: DIT/FT/2A/03

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Student_System.Model.Student;
import Student_System.Panels.ModulePanel;
import Student_System.Panels.ResultsPanel;
import Student_System.Panels.SearchPanel;
import Student_System.Panels.StudentPanel;

import java.awt.*;
import java.util.*;

public class MainFrame extends JFrame {
    private StudentPanel studentPanel;
    private ModulePanel modulePanel;
    private SearchPanel searchPanel;
    private ResultsPanel resultsPanel;
    private ArrayList<Student> students;

    public MainFrame(ArrayList<Student> students) {
        this.students = students;

        // Set the main frame properties
        setTitle("Student System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setSize(800, 600);

        // Initialize the panels
        modulePanel = new ModulePanel();
        studentPanel = new StudentPanel(students, modulePanel);
        resultsPanel = new ResultsPanel();
        searchPanel = new SearchPanel(students, studentPanel, resultsPanel);

        // Create the main panel with a GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add margin around the panel

        // Set GridBagConstraints for layout management
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding around components
        // Equal weight for horizontal space distribution
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;

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

        // Add the main panel to the frame
        add(mainPanel);

        // Adding the Refresh button directly in the MainFrame class
        JButton refreshButton = new JButton("Refresh");

        // Customize button
        refreshButton.setPreferredSize(new Dimension(100, 30)); // Smaller size
        refreshButton.setBackground(Color.GREEN); // Dark yellow background
        refreshButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, true)); // Rounded border

        // Action listener to refresh the whole JFrame
        refreshButton.addActionListener(e -> refreshPanels());

        // Create a panel for the refresh button and add it to the bottom center
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(refreshButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Method to refresh the student and module panels
    private void refreshPanels() {
        studentPanel.setStudents(students);
        resultsPanel.clearResults();
        searchPanel.clearSearchField();
    }
}
