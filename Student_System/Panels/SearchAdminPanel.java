package Student_System.Panels;

//Name: Syed Saud Ali
//Admission Number: p2209111
//Class: DIT/FT/2A/03

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import Student_System.Model.Student;
import Student_System.Utilities.StudentFileManager;

public class SearchAdminPanel extends JPanel {
    private JRadioButton searchByAdminNo, searchByName;
    private JTextField searchField;
    private JButton searchButton;
    private StudentAdminPanel studentpanel;
    private ResultsAdminPanel resultpanel;
    private ModuleAdminPanel modulepanel;
    // input fields from student panel
    private LabelledInputPanel namePanel, adminPanel, classPanel, gpaPanel;
    // buttons from module and student panel
    JButton createButton, deleteButton, addButton;

    public SearchAdminPanel(StudentAdminPanel studentpanel, ResultsAdminPanel resultpanel,
            ModuleAdminPanel modulepanel) {
        this.studentpanel = studentpanel;
        this.resultpanel = resultpanel;
        this.modulepanel = modulepanel;

        // get each of the panels from studentpanel
        namePanel = studentpanel.getStudentNamePanel();
        adminPanel = studentpanel.getStudentAdminPanel();
        classPanel = studentpanel.getStudentClassPanel();
        gpaPanel = studentpanel.getStudentGPAPanel();
        // get buttons from studentpanel
        createButton = studentpanel.getCreateButton();
        deleteButton = studentpanel.getDeleteButton();
        addButton = modulepanel.getAddButton();

        // Set the borders with margins
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Search");
        EmptyBorder innerMargin = new EmptyBorder(10, 10, 10, 10);
        setBorder(new CompoundBorder(titledBorder, innerMargin));

        // Set the main layout and preferred size
        setLayout(new BorderLayout(5, 5));
        setPreferredSize(new Dimension(300, 200));

        // Initialize components
        searchByAdminNo = new JRadioButton("By Admin");
        searchByName = new JRadioButton("By Name");
        ButtonGroup searchGroup = new ButtonGroup();
        searchGroup.add(searchByAdminNo);
        searchGroup.add(searchByName);
        searchField = new JTextField();
        searchButton = new JButton("Search");

        // Customize the search button
        searchButton.setPreferredSize(new Dimension(100, 30)); // Smaller size
        searchButton.setBackground(new Color(255, 204, 0)); // Dark yellow background
        searchButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true)); // Rounded border

        // Create a separate panel for radio buttons with FlowLayout
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioPanel.add(searchByAdminNo);
        radioPanel.add(searchByName);

        // Align the button to the right within the panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 10));
        buttonPanel.add(searchButton);

        // Add components to the main panel
        add(radioPanel, BorderLayout.NORTH); // Add radio buttons panel
        add(searchField, BorderLayout.CENTER); // Add search field
        add(buttonPanel, BorderLayout.SOUTH);// Add button panel

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement logic to filter by student admin number
                if (searchByAdminNo.isSelected()) {
                    System.out.println("Admin Number Selected");
                    String adminNo = searchField.getText().trim();
                    searchByAdmNo(adminNo);
                } // Implement logic to filter by student name
                else if (searchByName.isSelected()) {
                    System.out.println("Name Selected");
                    String studentName = searchField.getText().trim();
                    searchByName(studentName);
                }
            }
        });

    }

    private void searchByName(String name) {
        ArrayList<Student> students = StudentFileManager.loadStudentsFromFile("Student_System\\students.txt"); // Load students
        boolean found = false;

        for (Student student : students) {
            if (name.equalsIgnoreCase(student.getName())) {
                found = true;
                // Update the student panel with the found student's information
                namePanel.setText(student.getName());
                adminPanel.setText(student.getAdminNumber());
                classPanel.setText(student.getCourseClass());
                gpaPanel.setText(String.format("%.2f", student.calculateGPA()));

                // Set editable fields to false
                namePanel.SetEditable(false);
                adminPanel.SetEditable(false);
                classPanel.SetEditable(false);

                // Enable the Add and Delete buttons, disable the Create button
                addButton.setEnabled(true);
                deleteButton.setEnabled(true);
                createButton.setEnabled(false);

                // Set text in results panel
                resultpanel.TextinJTextArea("Student has been Found!");
                break;
            }
        }

        if (!found) {
            resultpanel.TextinJTextArea("Student has not been Found!");
            namePanel.setText("");
            adminPanel.setText("");
            classPanel.setText("");
            gpaPanel.setText("0.00");

            namePanel.SetEditable(true);
            adminPanel.SetEditable(true);
            classPanel.SetEditable(true);

            addButton.setEnabled(false);
            createButton.setEnabled(true);
            deleteButton.setEnabled(false);
        }
    }

    private void searchByAdmNo(String admNo) {
        ArrayList<Student> students = StudentFileManager.loadStudentsFromFile("Student_System\\students.txt"); // Load students
        boolean found = false;
        for (Student student : students) {
            if (admNo.equalsIgnoreCase(student.getAdminNumber())) {
                found = true;
                // Update the student panel with the found student's information
                namePanel.setText(student.getName());
                adminPanel.setText(student.getAdminNumber());
                classPanel.setText(student.getCourseClass());
                gpaPanel.setText(String.format("%.2f", student.calculateGPA()));
                // Set editable fields to false
                namePanel.SetEditable(false);
                adminPanel.SetEditable(false);
                classPanel.SetEditable(false);
                // Enable the Add and Delete buttons, disable the Create button
                addButton.setEnabled(true);
                deleteButton.setEnabled(true);
                createButton.setEnabled(false);
                // Set text in results panel
                resultpanel.TextinJTextArea("Student has been Found!");
                break;
            }
        }
        if (!found) {
            resultpanel.TextinJTextArea("Student has not been Found!");
            namePanel.setText("");
            adminPanel.setText("");
            classPanel.setText("");
            gpaPanel.setText("0.00");

            namePanel.SetEditable(true);
            adminPanel.SetEditable(true);
            classPanel.SetEditable(true);

            addButton.setEnabled(false);
            createButton.setEnabled(true);
            deleteButton.setEnabled(false);
        }
    }
}
