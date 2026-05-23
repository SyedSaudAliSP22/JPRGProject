package Student_System.Panels;

//Name:Tharik Parvas S/O Checkoussain
//Admission Number: p2349288
//Class: DIT/FT/2A/03

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.*;

import Student_System.Model.Student;

public class SearchPanel extends JPanel {
    private JRadioButton searchByClass, searchByName;
    private JTextField searchField;
    private JButton searchButton;
    private ArrayList<Student> students;
    private StudentPanel studentPanel;
    private ResultsPanel resultsPanel;

    public SearchPanel(ArrayList<Student> students, StudentPanel studentPanel, ResultsPanel resultsPanel) {

        this.students = students;
        this.studentPanel = studentPanel;
        this.resultsPanel = resultsPanel;

        // Set the borders with margins
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Search");
        EmptyBorder innerMargin = new EmptyBorder(10, 10, 10, 10);
        setBorder(new CompoundBorder(titledBorder, innerMargin));

        // Set the main layout and preferred size
        setLayout(new BorderLayout(5, 5));
        setPreferredSize(new Dimension(300, 200));

        // Initialize components
        searchByClass = new JRadioButton("By Class");
        searchByName = new JRadioButton("By Name");
        ButtonGroup searchGroup = new ButtonGroup();
        searchGroup.add(searchByClass);
        searchGroup.add(searchByName);
        searchField = new JTextField();
        searchButton = new JButton("Search");

        // Customize the search button
        searchButton.setPreferredSize(new Dimension(100, 30)); // Smaller size
        searchButton.setBackground(new Color(255, 204, 0)); // Dark yellow background
        searchButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true)); // Rounded border

        // Create a separate panel for radio buttons with FlowLayout
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioPanel.add(searchByClass);
        radioPanel.add(searchByName);

        // Align the button to the right within the panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 10));
        buttonPanel.add(searchButton);

        // Add components to the main panel
        add(radioPanel, BorderLayout.NORTH); // Add radio buttons panel
        add(searchField, BorderLayout.CENTER); // Add search field
        add(buttonPanel, BorderLayout.SOUTH);

        // Action Listener for radio buttons
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText().trim();
                if (searchByClass.isSelected()) {
                    searchByClass(query);
                }

                if (searchByName.isSelected()) {
                    searchByName(query);
                }
            }
        });
    }

    private void searchByClass(String className) {
        ArrayList<Student> classStudents = new ArrayList<>();

        StringBuilder results = new StringBuilder();
        double totalGPA = 0.0;

        // Iterate through the list of students to find matches by class
        for (Student student : students) {
            if (className.equalsIgnoreCase(student.getCourseClass())) {
                classStudents.add(student);
                totalGPA += student.calculateGPA(); // Calculate the total GPA
            }
        }

        if (classStudents.size() > 0) {
            studentPanel.setStudents(classStudents); // Display the students in the student panel
            double avgGPA = totalGPA / classStudents.size(); // Calculate the average GPA
            results.append("Number of student(s) in ").append(className.toUpperCase()).append(": ")
                    .append(classStudents.size()).append("\n");
            results.append("Average GPA: ").append(String.format("%.2f", avgGPA));
        } else {
            results.append("Class cannot be found!");
            studentPanel.clearStudentInfo(); // Clear student info in the panel
        }

        resultsPanel.displayResults(results.toString()); // Display the results in the results panel
    }

    private void searchByName(String name) {
        boolean found = false;
        ArrayList<Student> searchedStudent = new ArrayList<>();

        // Iterate through the students to find the matching name
        for (Student student : students) {
            if (name.equalsIgnoreCase(student.getName())) {
                found = true;
                searchedStudent.add(student);
                break; // Stop searching after the first match is found
            }
        }

        if (found) {
            // Display the found student's information
            studentPanel.setStudents(searchedStudent);
        } else {
            // Clear student and module panels if no student is found
            resultsPanel.displayResults("No such student found");
            studentPanel.clearStudentInfo();
        }
    }

    public void clearSearchField() {
        searchField.setText(""); // Clear the text in the search field
    }

}
