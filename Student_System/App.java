package Student_System;

//Name:Tharik Parvas S/O Checkoussain
//Admission Number: p2349288
//Class: DIT/FT/2A/03

import java.util.*;
import javax.swing.*;

import Student_System.Frame.MainFrame;
import Student_System.Model.Student;
import Student_System.Utilities.StudentFileManager;

public class App {
    public static void main(String[] args) {
        // Initialize JFrame with Extracted Student List
        SwingUtilities.invokeLater(() -> {
            ArrayList<Student> students = StudentFileManager.loadStudentsFromFile("Student_System\\students.txt");
            new MainFrame(students);
        });
    }
}
