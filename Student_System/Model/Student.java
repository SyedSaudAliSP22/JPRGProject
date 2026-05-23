package Student_System.Model;

//Name: Syed Saud Ali, Tharik Parvas S/O Checkoussain
//Admission Number: p2209111, p2349288
//Class: DIT/FT/2A/03

import java.util.ArrayList;

public class Student {
    private String name;
    private String adminNumber;
    private String courseClass;
    private ArrayList<Module> modules;

    public Student(String name, String adminNumber, String courseClass, ArrayList<Module> modules) {
        this.name = name;
        this.adminNumber = adminNumber;
        this.courseClass = courseClass;
        this.modules = modules;
    }

    public String getName() {
        return name;
    }

    public String getAdminNumber() {
        return adminNumber;
    }

    public String getCourseClass() {
        return courseClass;
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    // Calculate GPA for each student
    public double calculateGPA() {
        double totalCredits = 0.0;
        double totalCreditPoints = 0.0;

        // If student has no modules, return 0 as gpa
        if (modules == null || modules.isEmpty()) {
            return 0.0; // Return 0 if there are no modules
        }

        for (int i = 0; i < modules.size(); i++) {
            Module module = modules.get(i);
            int gradePoints = module.getGradePoints();
            int credit = module.getCredit();

            totalCreditPoints += credit * gradePoints;
            totalCredits += credit;
        }

        // Return the calculated GPA
        return totalCreditPoints / totalCredits;
    }

    public String toString() {
        return "\nName: " + this.name + "\nAdmin: " + this.adminNumber + "\nClass: " + this.courseClass;
    }
}
