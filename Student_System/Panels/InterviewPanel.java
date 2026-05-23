package Student_System.Panels;

//Name:Tharik Parvas S/O Checkoussain
//Admission Number: p2349288
//Class: DIT/FT/2A/03

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Student_System.Model.Student;
import Student_System.Utilities.StudentFileManager;

public class InterviewPanel extends JPanel {
    private LabelledInputPanel classPanel, startTimePanel, intervalPanel;
    private JButton scheduleButton;
    private JTable scheduleTable;
    private DefaultTableModel tableModel;
    ArrayList<Student> students = StudentFileManager.loadStudentsFromFile("Student_System\\students.txt"); // Load students

    public InterviewPanel() {

        setLayout(new BorderLayout(10, 10));
        setPreferredSize(new Dimension(700, 200)); // Set preferred size for interview panel

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setPreferredSize(new Dimension(240, 100));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Interview Schedule"));

        // Initialize input panels
        classPanel = new LabelledInputPanel("Class:");
        startTimePanel = new LabelledInputPanel("Start Time (HH:mm):");
        intervalPanel = new LabelledInputPanel("Interval (Minutes):");

        // Add input panels to the input panel container
        inputPanel.add(classPanel);
        inputPanel.add(startTimePanel);
        inputPanel.add(intervalPanel);

        // Customize Schedule Button
        scheduleButton = new JButton("Generate Schedule");
        scheduleButton.setPreferredSize(new Dimension(60, 40));
        scheduleButton.setBackground(new Color(247, 50, 212));
        scheduleButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true)); // Rounded border

        // Schedule Table setup with column names and 0 rows
        String[] columnNames = { "Admin Number", "Time Slot", "Student Name" };
        tableModel = new DefaultTableModel(columnNames, 0);
        scheduleTable = new JTable(tableModel);

        // Add table to scroll pane for scrollability in case of large dataset
        JScrollPane scrollPane = new JScrollPane(scheduleTable);
        scheduleTable.setFillsViewportHeight(true);

        scheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseClassInput = classPanel.getText().trim();
                String startTimeInput = startTimePanel.getText().trim();
                String intervalInput = intervalPanel.getText().trim();

                // Check if any field is empty
                if (courseClassInput.isEmpty() || startTimeInput.isEmpty() || intervalInput.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled out", "Error",
                            JOptionPane.ERROR_MESSAGE);

                    return;
                }

                try {
                    int interval = Integer.parseInt(intervalInput);
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime slotTime = LocalTime.parse(startTimeInput, timeFormatter);

                    // Fetch students by class
                    ArrayList<Student> interviewList = getStudentsByClass(courseClassInput);

                    tableModel.setRowCount(0); // Clear existing rows in the table
                    for (Student student : interviewList) {
                        String adminNumber = student.getAdminNumber();
                        String timeSlot = slotTime.format(timeFormatter);
                        String studentName = student.getName();

                        // Add new row to the table
                        tableModel.addRow(new String[] { adminNumber, timeSlot, studentName });

                        // Increment the slot time by the interval
                        slotTime = slotTime.plusMinutes(interval);
                    }

                } catch (Exception ex) {
                    // Handle invalid input or format
                    JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add components to panel
        add(inputPanel, BorderLayout.WEST);
        add(scheduleButton, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.EAST);
    }

    // Method to get students by class
    private ArrayList<Student> getStudentsByClass(String classInput) {
        ArrayList<Student> classStudents = new ArrayList<>();

        for (Student student : students) {
            // Check if the student's class matches the input class
            if (classInput.equalsIgnoreCase(student.getCourseClass())) {
                classStudents.add(student);
            }
        }

        return classStudents;
    }
}
