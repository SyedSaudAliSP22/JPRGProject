package Student_System.Panels;

//Name:Tharik Parvas S/O Checkoussain
//Admission Number: p2349288
//Class: DIT/FT/2A/03

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import Student_System.Model.Student;

public class StudentPanel extends JPanel {
    private LabelledInputPanel studentNamePanel, studentAdminPanel, studentClassPanel, studentGPAPanel;
    private JButton studentNextButton, studentPrevButton;
    private ArrayList<Student> students;
    private int currentIndex = 0;
    private ModulePanel modulePanel;

    public StudentPanel(ArrayList<Student> students, ModulePanel modulePanel) {
        this.students = students;
        this.modulePanel = modulePanel;

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(350, 200));

        // Initialize input panels
        studentNamePanel = new LabelledInputPanel("Name:");
        studentAdminPanel = new LabelledInputPanel("Admin:");
        studentClassPanel = new LabelledInputPanel("Class:");
        studentGPAPanel = new LabelledInputPanel("GPA:");

        // Initialize buttons
        studentNextButton = new JButton("Next");
        studentPrevButton = new JButton("Prev");

        // Disable input fields to make it readonly
        studentNamePanel.SetEditable(false);
        studentAdminPanel.SetEditable(false);
        studentClassPanel.SetEditable(false);
        studentGPAPanel.SetEditable(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components to the panel with specific grid positions
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(studentNamePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(studentAdminPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(studentClassPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(studentGPAPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(studentNextButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(studentPrevButton, gbc);

        studentNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex < students.size() - 1) { // Check if there are more students
                    currentIndex++;
                    displayStudentInfo(currentIndex);
                    updateButtonStates();

                }
            }
        });

        studentPrevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex > 0) { // Check if there is a previous student
                    currentIndex--;
                    displayStudentInfo(currentIndex);
                    updateButtonStates();

                }
            }
        });

        // Initialize with the first student's information and update button states
        displayStudentInfo(currentIndex);
        updateButtonStates();
    }

    // Display the information of a student based on the given index
    private void displayStudentInfo(int index) {
        if (students.isEmpty()) {
            clearStudentInfo();
            return;
        }
        Student student = students.get(index);
        studentNamePanel.setText(student.getName());
        studentAdminPanel.setText(student.getAdminNumber());
        studentClassPanel.setText(student.getCourseClass());
        studentGPAPanel.setText(String.format("%.2f", student.calculateGPA()));
        // Update the border title with the current student number
        setBorder(BorderFactory.createTitledBorder("Student " + (index + 1) + " of " + students.size()));

        // Update module panel with the current student's modules
        modulePanel.setModules(student.getModules());
    }

    // Method to set the Student list to display
    public void setStudents(ArrayList<Student> students) {
        this.students = students;
        currentIndex = 0;
        if (!students.isEmpty()) {
            displayStudentInfo(currentIndex);
        } else {
            clearStudentInfo();
        }
        updateButtonStates();
    }

    // Clear the displayed student information and module info
    public void clearStudentInfo() {
        studentNamePanel.setText("");
        studentAdminPanel.setText("");
        studentClassPanel.setText("");
        studentGPAPanel.setText("");
        modulePanel.clearModuleInfo();
        setBorder(BorderFactory.createTitledBorder("Student 0 of 0"));
        studentPrevButton.setEnabled(false);
        studentNextButton.setEnabled(false);
    }

    // Update the state of the navigation buttons based on the current index
    private void updateButtonStates() {
        studentPrevButton.setEnabled(currentIndex > 0);
        studentNextButton.setEnabled(currentIndex < students.size() - 1);
    }
}
