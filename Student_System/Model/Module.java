package Student_System.Model;

//Name: Syed Saud Ali, Tharik Parvas S/O Checkoussain
//Admission Number: p2209111, p2349288
//Class: DIT/FT/2A/03

public class Module {
    private String code;
    private String name;
    private int credit;
    private double marks;

    public Module(String code, String name, int credit, double marks) {
        this.code = code;
        this.name = name;
        this.credit = credit;
        this.marks = marks;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCredit() {
        return credit;
    }

    public double getMarks() {
        return marks;
    }

    // Convert student's mark to equivelent grade point per module
    public int getGradePoints() {
        int gradePoint = 0;

        if (this.marks >= 80) {
            gradePoint = 4;
        } else if (this.marks >= 70) {
            gradePoint = 3;
        } else if (this.marks >= 60) {
            gradePoint = 2;
        } else if (this.marks >= 50) {
            gradePoint = 1;
        } else {
            gradePoint = 0;
        }

        return gradePoint;
    }

    // Convert student's mark to equivelent grade per module
    public char getGrade() {
        char grade;

        if (this.marks >= 80) {
            grade = 'A';
        } else if (this.marks >= 70) {
            grade = 'B';
        } else if (this.marks >= 60) {
            grade = 'C';
        } else if (this.marks >= 50) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        return grade;
    }

    public String toString() {
        return this.code + "/" + this.name + "/" + this.credit + ": " + getGrade();
    }
}
