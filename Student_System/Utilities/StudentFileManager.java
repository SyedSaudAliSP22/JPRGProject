package Student_System.Utilities;

//Name: Syed Saud Ali, Tharik Parvas S/O Checkoussain
//Admission Number: p2209111, p2349288
//Class: DIT/FT/2A/03

import java.io.*;
import java.util.*;

import Student_System.Model.Module;
import Student_System.Model.Student;
import Student_System.Panels.ResultsAdminPanel;

public class StudentFileManager {
    // ============================================================================================================================
    // Tharik's side
    public static ArrayList<Student> loadStudentsFromFile(String fileName) {

        ArrayList<Student> students = new ArrayList<>();
        File inputFile = new File(fileName);

        // Use a try-with-resources statement to automatically close the BufferedReader
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {

            // Read the first line from the file which contains the total number of students
            String line = br.readLine();
            if (line == null || line.trim().isEmpty()) {
                System.err.println("The file is empty or improperly formatted.");
                return students; // Return empty list
            }

            int totalStudents;
            try {
                totalStudents = Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.err.println("First line of the file should be the total number of students.");
                return students;
            }

            // Loop through the number of students specified
            for (int i = 0; i < totalStudents; i++) {
                // Read the next line and split it into student data based on semicolons
                String[] studentData = br.readLine().split(";");
                // Extract student details from the data
                String courseClass = studentData[0];
                String adminNumber = studentData[1];
                String studentName = studentData[2];

                int moduleCount;
                try {
                    moduleCount = Integer.parseInt(studentData[3]); // Parse the number of modules
                } catch (NumberFormatException e) {
                    System.err.println("Invalid module count for student: " + studentName);
                    continue; // Skip this student if module count is invalid
                }

                ArrayList<Module> modules = new ArrayList<>();
                int modIndex = 4; // Start index for module data

                try {
                    // Loop through each module and extract details
                    for (int j = 0; j < moduleCount; j++) {
                        String code = studentData[modIndex++];
                        String name = studentData[modIndex++];
                        int credit = Integer.parseInt(studentData[modIndex++]);
                        double marks = Double.parseDouble(studentData[modIndex++]);
                        modules.add(new Module(code, name, credit, marks));
                    }
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.err.println("Error parsing module information for student: " + studentName);
                    continue; // Skip this student if there is an error with module data
                }

                // Create a Student object and add it to the list
                Student student = new Student(studentName, adminNumber, courseClass, modules);
                students.add(student);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO Exception: " + e.getMessage());
        }

        // Return the list of students retrived from txt file
        return students;
    }

    // ============================================================================================================================
    // Ali's side
    // method to add new student to txt file
    public void AddStudent(String Student, String admNo, ResultsAdminPanel resultpanel) {
        try {
            // Create a BufferedWriter object with FileWriter to append to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter("Student_System\\students.txt", true));
            // add a new line before adding new students
            writer.newLine();
            writer.write(Student);
            // Close the writer
            writer.close();
            // show that adding the student is successful
            resultpanel.TextinJTextArea("Student " + admNo + " has been added Successfully!");
        } catch (IOException e) {
            // Catch any exceptions that occur and print the error message
            System.out.println("Exception occurred: " + e);
        }
    }

    // method to delete student from txt file
    public void DeleteStudent(String Info, String admNo, ResultsAdminPanel resultpanel) {
        try {
            String file = "Student_System\\students.txt";
            Scanner sc = new Scanner(new File(file));
            StringBuffer buffer = new StringBuffer();
            boolean flag = false;
            int count = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (!line.contains(Info)) {
                    // if student is not found, add data to the list
                    buffer.append(line);
                    if (sc.hasNextLine()) {
                        buffer.append(System.lineSeparator());
                    }
                } else {
                    // once found dont add in the file content
                    flag = true;
                    count++;
                }
            }
            String fileContents = buffer.toString();
            FileWriter writer = new FileWriter(file);
            writer.append(fileContents);
            // close the writer
            writer.close();
            sc.close();
            // show that deleting the student is successful
            resultpanel.TextinJTextArea("Student " + admNo + " has been deleted Successfully!");
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }

    // method to update student total value
    public void TotalStudent(boolean isIncrement) {
        // update the number inside the textfile to show the total number of students
        if (isIncrement) {
            try {
                // Instantiating the File class
                String file = "Student_System\\students.txt";
                // Instantiating the Scanner class to read the file
                Scanner sc = new Scanner(new File(file));
                // instantiating the StringBuffer class
                StringBuffer buffer = new StringBuffer();
                // Reading lines of the file and appending them to StringBuffer
                while (sc.hasNextLine()) {
                    buffer.append(sc.nextLine());
                    if (sc.hasNextLine()) {
                        // only add line seperator if there are new lines
                        buffer.append(System.lineSeparator());
                    }
                }
                String fileContents = buffer.toString();
                // put the lines into an array
                String[] lines = fileContents.split(System.lineSeparator());
                // get the number of students
                String outDatedStudentNo = lines[0];
                // closing the Scanner object
                sc.close();
                int oldTotal = Integer.parseInt(outDatedStudentNo.trim());
                int newTotal = oldTotal + 1;
                String updatedStudentNo = String.valueOf(newTotal);
                // Replacing the old line with new line
                fileContents = fileContents.replaceFirst(String.valueOf(oldTotal), updatedStudentNo);
                // instantiating the FileWriter class
                FileWriter writer = new FileWriter(file);
                writer.append(fileContents);
                writer.close();
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        } else {
            try {
                // Instantiating the File class
                String file = "Student_System\\students.txt";
                // Instantiating the Scanner class to read the file
                Scanner sc = new Scanner(new File(file));
                // instantiating the StringBuffer class
                StringBuffer buffer = new StringBuffer();
                // Reading lines of the file and appending them to StringBuffer
                while (sc.hasNextLine()) {
                    buffer.append(sc.nextLine());
                    if (sc.hasNextLine()) {
                        // only add line seperator if there are new lines
                        buffer.append(System.lineSeparator());
                    }
                }
                String fileContents = buffer.toString();
                // put the lines into an array
                String[] lines = fileContents.split(System.lineSeparator());
                // get the number of students
                String outDatedStudentNo = lines[0];
                // closing the Scanner object
                sc.close();
                int oldTotal = Integer.parseInt(outDatedStudentNo.trim());
                int newTotal = oldTotal - 1;
                String updatedStudentNo = String.valueOf(newTotal);
                // Replacing the old line with new line
                fileContents = fileContents.replaceFirst(String.valueOf(oldTotal), updatedStudentNo);
                // instantiating the FileWriter class
                FileWriter writer = new FileWriter(file);
                writer.append(fileContents);
                writer.close();
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
