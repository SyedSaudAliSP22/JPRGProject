package Student_System.Panels;

//Name: Syed Saud Ali
//Admission Number: p2209111
//Class: DIT/FT/2A/03

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Student_System.Utilities.StudentFileManager;

public class StudentAdminPanel extends JPanel {
    private LabelledInputPanel studentNamePanel, studentAdminPanel, studentClassPanel, studentGPAPanel;
    private JButton studentCreateButton, studentDeleteButton;
    private ResultsAdminPanel resultpanel;
    private StudentFileManager studentFileManager;

    public StudentAdminPanel(ResultsAdminPanel resultpanel) {
        this.resultpanel = resultpanel;
        this.studentFileManager = new StudentFileManager();
        setBorder(BorderFactory.createTitledBorder("Student"));
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(350, 200)); // Set button size

        // Initialize components using LabelledInputPanel
        studentNamePanel = new LabelledInputPanel("Name:");
        studentAdminPanel = new LabelledInputPanel("Admin:");
        studentClassPanel = new LabelledInputPanel("Class:");
        studentGPAPanel = new LabelledInputPanel("GPA:");
        // set GPA editability to false
        studentGPAPanel.SetEditable(false);
        // set GPA value to 0.0 as no student has been created yet
        studentGPAPanel.setText("0.00");

        studentCreateButton = new JButton("Create");
        studentDeleteButton = new JButton("Delete");
        // disable editbutton
        studentDeleteButton.setEnabled(false);
        // Layout constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Add LabelledInputPanels
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

        // Add buttons
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(studentCreateButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(studentDeleteButton, gbc);

        studentCreateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement logic to create new student
                String newStudentInformation = studentClassPanel.getText().trim() + ";" +
                        studentAdminPanel.getText().trim() + ";"
                        + studentNamePanel.getText().trim() + ";"
                        + "0;";
                System.out.println("New Student Information: " + newStudentInformation);
                // add new student to file
                studentFileManager.AddStudent(newStudentInformation, studentAdminPanel.getText().trim(), resultpanel);
                // clear output
                studentNamePanel.setText("");
                studentAdminPanel.setText("");
                studentClassPanel.setText("");
                // increment the total by 1
                studentFileManager.TotalStudent(true);
            }
        });

        studentDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement logic to delete existing student
                // save student information to be used to delete information
                String studentInformation = studentClassPanel.getText().trim() + ";" +
                        studentAdminPanel.getText().trim() + ";"
                        + studentNamePanel.getText().trim() + ";";
                System.out.println(studentInformation);
                // call the method that deletes students
                studentFileManager.DeleteStudent(studentInformation, studentAdminPanel.getText().trim(), resultpanel);
                // decrement total by 1
                studentFileManager.TotalStudent(false);
                // clear output
                studentNamePanel.setText("");
                studentAdminPanel.setText("");
                studentClassPanel.setText("");
                studentGPAPanel.setText("0.00");
                studentCreateButton.setEnabled(true);
                studentDeleteButton.setEnabled(false);
            }
        });
    }

    public LabelledInputPanel getStudentNamePanel() {
        return studentNamePanel;
    }

    // get student admin panel and use it in other parts of the code
    public LabelledInputPanel getStudentAdminPanel() {
        return studentAdminPanel;
    }

    // get student class panel and use it in other parts of the code
    public LabelledInputPanel getStudentClassPanel() {
        return studentClassPanel;
    }

    // get student GPA panel and use it in other parts of the code
    public LabelledInputPanel getStudentGPAPanel() {
        return studentGPAPanel;
    }

    public JButton getCreateButton() {
        return studentCreateButton;
    }

    public JButton getDeleteButton() {
        return studentDeleteButton;
    }
}
