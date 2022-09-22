import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Write a description of class StudentMark here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */



public class StudentMark {

    //arrayList to store students objects
    public static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        
        //First we need to read the file because every method needs that file and students arrayList
        readFile();
        //Also, we need the 'Total marks' for the calculations and perform functions correctly
        calTotal();

        //Main Menu
        menu();

    }

    //F5: Create a simple menu system to allow
    //users to select and execute each function
    public static void menu(){ //F5

        System.out.println("\t\tWELCOME TO THE CLASS STUDENT MARKS SYSTEM MANAGER");
        System.out.println();
        System.out.println("\tMAIN MENU");
        System.out.println();
        System.out.println("F1: Read the unit name and students’ marks\n" +
                "    from the given text file");
        System.out.println();
        System.out.println("F2: Calculate the total mark for each student\n" +
                "    from the assessment marks and print out the\n" +
                "    list of students with their name, student id,\n" +
                "    assessment marks and the total mark");
        System.out.println();
        System.out.println("F3: Print the list of students with the total\n" +
                "    marks less than a certain threshold. The\n" +
                "    threshold will be entered from keyboard");
        System.out.println();
        System.out.println("F4: Print the top 10 students with the highest\n" +
                "    total marks and top 10 students with the\n" +
                "    lowest total marks");
        System.out.println();
        System.out.println("PRESS 0 FOR EXIT");

        System.out.println("ENTER THE FUNCTION YOU WANT TO RUN (1 / 2 / 3 / 4 / 0) :");
        int choice = (new Scanner(System.in)).nextInt();

        switch (choice){
            case 1:
                readFile();
                System.out.println("File Readed !");
                menu();
                break;
            case 2:
                print();
                menu();
                break;
            case 3:
                printLessThan();
                menu();
                break;
            case 4:
                sort();
                menu();
                break;
            case 0:
                break;
            default:
                System.out.println("Wrong input!!! Please try again...");
                menu();

        }
    }

    //F1: Read the unit name and students’ marks
    //from a given text file. The file contains the
    //unit name and the list of students with their
    //names, student ids and marks for three
    //assignments. The file also contains lines,
    //which are comments and your program
    //should check to ignore them when reading
    //the students’ marks.

    public static void readFile(){ //F1
        try {
            FileReader file = new FileReader("prog5001_students_grade_2022.csv");
            BufferedReader br = new BufferedReader(file);
            String unitName = br.readLine();
            String titles = br.readLine();

            String lastName, firstName, studentID;
            double a1, a2,a3;

            String line = br.readLine();
            while (line != null) {
                //split read line by ","
                String[] studentDetails = line.split(",", -1);

                for(int j = 0 ; j < studentDetails.length ; j++){
                    if(studentDetails[j].equals("")){
                        studentDetails[j] = "0.0";
                    }
                }

                //assign all values to variables
                lastName = studentDetails[0];
                firstName = studentDetails[1];
                studentID = studentDetails[2];
                a1 = Double.parseDouble(studentDetails[3]);
                a2 = Double.parseDouble(studentDetails[4]);
                a3 = Double.parseDouble(studentDetails[5]);

                // create student object and add it to students arrayList
                students.add(new Student(lastName,firstName,studentID,a1,a2,a3));

                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //F2: Calculate the total mark for each student
    //from the assessment marks and print out the
    //list of students with their name, student id,
    //assessment marks and the total mark

    public static void print(){ //F2

        calTotal();

        // print details with total mark
        for (Student student : StudentMark.students){
            System.out.println("Student Name : " + student.firstName + " " + student.lastName);
            System.out.println("Student ID : " + student.StudentID);
            System.out.println("Assessment Mark A1 : " + student.A1);
            System.out.println("Assessment Mark A2 : " + student.A2);
            System.out.println("Assessment Mark A3 : " + student.A3);
            System.out.println("Total Marks : " + student.totalMark);
            System.out.println();
        }
    }
    public static void calTotal(){
        //total marks calculate and assigned it to variable named 'totalMark' in the student class
        for (Student student : StudentMark.students){
            student.totalMark = student.A1 + student.A2 + student.A3;
        }
    }

    //F3: Print the list of students with the total
    //marks less than a certain threshold. The
    //threshold will be entered from keyboard

    public static void printLessThan(){ //F3
        //find total mark
        calTotal();

        System.out.println("Input a number between 0 and 100 ");
        double threshold =(new Scanner(System.in)).nextDouble();

        if(threshold >=0 && threshold <= 100){
            System.out.println("List of students with the total marks less than " + threshold);
            for (Student student : StudentMark.students){
                if(threshold > student.totalMark){
                    System.out.println(student.firstName + " " + student.lastName + "(" + student.totalMark + ")");
                }
            }
        }else {
            System.out.println("Please check the input and try again !!!");
            printLessThan();
        }


    }

    //F4: Print the top 10 students with the highest
    //total marks and top 10 students with the
    //lowest total marks

    public static void sort(){ //F4

        //find total mark
        calTotal();

        //make a student array from arrayList to perform bubble sort
        Student[] studentArr = students.toArray(new Student[0]);

        //I used bubble sort as the sorting algorithm
        for (int i = 0; i < studentArr.length - 1; i++) {

            for (int j = 0; j < studentArr.length - i - 1; j++) {

                if (studentArr[j].totalMark > studentArr[j + 1].totalMark) {
                    Student temp = studentArr[j];
                    studentArr[j] = studentArr[j + 1];
                    studentArr[j + 1] = temp;
                }
            }
        }

        System.out.println("Top 10 students with the lowest total marks");
        for (int i = 0; i < 10 ; i++){
            System.out.println(studentArr[i].firstName + " : " + studentArr[i].totalMark);
        }

        System.out.println();


        System.out.println("Top 10 students with the highest total marks");
        for (int i = 1 ; i <= 10  ; i++){
            System.out.println(studentArr[studentArr.length - i].firstName + " " + studentArr[studentArr.length - i].totalMark);
        }

    }
}

