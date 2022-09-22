import java.util.Scanner;

/**
 * Write a description of class Student here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Student {
    String lastName;
    String firstName;
    String StudentID;
    double A1;
    double A2;
    double A3;

    double totalMark;

    public Student(String lastName, String firstName, String studentID, double a1, double a2, double a3) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.StudentID = studentID;
        this.A1 = a1;
        this.A2 = a2;
        this.A3 = a3;
    }
}

