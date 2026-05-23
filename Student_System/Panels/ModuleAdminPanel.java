package Student_System.Panels;

//Name: Syed Saud Ali
//Admission Number: p2209111
//Class: DIT/FT/2A/03

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

import Student_System.Utilities.StudentFileManager;
import Student_System.Model.Student;

public class ModuleAdminPanel extends JPanel {
    private LabelledInputPanel moduleCodePanel, moduleNamePanel, moduleMarksPanel, moduleCreditPanel, gpaPanel;
    private JButton moduleAddButton;
    private StudentAdminPanel studentpanel;
    private ResultsAdminPanel resultpanel;

    public ModuleAdminPanel(StudentAdminPanel studentpanel, ResultsAdminPanel resultpanel) {
        this.studentpanel = studentpanel;
        this.resultpanel = resultpanel;
        setBorder(BorderFactory.createTitledBorder("Module"));
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(350, 230)); // Set button size

        // Initialize components using LabelledInputPanel
        moduleCodePanel = new LabelledInputPanel("Mod Code:");
        moduleNamePanel = new LabelledInputPanel("Mod Name:");
        moduleMarksPanel = new LabelledInputPanel("Marks:");
        moduleCreditPanel = new LabelledInputPanel("Credit:");
        // access GPA Panel
        gpaPanel = studentpanel.getStudentGPAPanel();
        moduleAddButton = new JButton("Add");
        moduleAddButton.setEnabled(false);

        // Create GridBagConstraints for layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Add LabelledInputPanels
        add(moduleCodePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(moduleNamePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(moduleMarksPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(moduleCreditPanel, gbc);

        // Add buttons
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(moduleAddButton, gbc);
        // dont press button yet it is still in process
        moduleAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement logic to add new modules to existing student
                String studentClass = studentpanel.getStudentClassPanel().getText().trim();
                String studentAdminNo = studentpanel.getStudentAdminPanel().getText().trim();
                String studentName = studentpanel.getStudentNamePanel().getText().trim();
                // get student information to match with the file content
                String studentInfo = studentClass + ";" + studentAdminNo + ";" + studentName + ";";
                System.out.println(studentInfo);
                // add module infotmation
                AddModuleToStudent(studentInfo);
                // show updated GPA information
                ArrayList<Student> students = StudentFileManager.loadStudentsFromFile("Student_System\\students.txt"); // Load students
                boolean found = false;
                for (Student student : students) {
                    if (studentName.equalsIgnoreCase(student.getName())) {
                        found = true;
                        gpaPanel.setText(String.format("%.2f", student.calculateGPA()));
                    }
                }
            }
        });
    }

    // method to get module add button
    public JButton getAddButton() {
        return moduleAddButton;
    }

    // method to add modules
    public void AddModuleToStudent(String info) {
        try {
            // read file
            String file = "Student_System\\students.txt";
            Scanner sc = new Scanner(new File(file));
            StringBuffer buffer = new StringBuffer();
            boolean flag = false;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                // only add content where it doesnt match with student information
                if (line.indexOf(info) != -1) {
                    flag = true;
                    String[] lines = line.split(";");
                    // update number of modules
                    int modNum = Integer.parseInt(lines[3]);
                    modNum++;
                    String newLine = "";
                    for (int i = 0; i < lines.length; i++) {
                        if (i != 3) {
                            newLine += lines[i] + ";";
                        } else {
                            newLine += String.valueOf(modNum) + ";";
                        }
                    }
                    // add new module information
                    newLine += moduleCodePanel.getText() + ";"
                            + moduleNamePanel.getText() + ";" + moduleCreditPanel.getText() + ";"
                            + moduleMarksPanel.getText() + ";";
                    buffer.append(newLine);
                } else {
                    buffer.append(line);
                }

                if (sc.hasNextLine()) {
                    buffer.append(System.lineSeparator());
                }
            }
            if (flag = true) {
                resultpanel.TextinJTextArea("Module have been added successfully");
            } else {
                resultpanel.TextinJTextArea("Module have not been added successfully");
            }
            String fileContents = buffer.toString();
            FileWriter writer = new FileWriter(file);
            writer.append(fileContents);
            // close writer and scanner
            writer.close();
            sc.close();
            // clear output
            moduleCodePanel.setText("");
            moduleNamePanel.setText("");
            moduleMarksPanel.setText("");
            moduleCreditPanel.setText("");
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }
}
