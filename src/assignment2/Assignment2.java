/**
 * Assignment 2 client
 * Michael John
 * 19/10/2019
 * Assignment2.java
 * 
 * 
 * 
 *
 * @author Michael John
 * @version 1.0
 * @since 19/10/2019
 */

package assignment2;

import java.io.IOException;
import java.util.Scanner;
import java.util.*;

public class Assignment2 {

    public static Scanner keyboard = new Scanner(System.in);
    public static int AMOUNTOFSTUDENTS = 20;
    public static Student[] studentList = new Student[AMOUNTOFSTUDENTS]; 
    public static int studentsInList;   // variable for amount of students enterred into the array, instead of the actual array length
    
    public static void main(String[] args){
        studentInfo(); // print my student info to screen
        
        //sets each index in the array as a blank student
        for(int i = 0; i < studentList.length; i++)
            studentList[i] = new Student();
        
        int menu = 0;
        

        // Loads student information from student.txt
        Student.loadFile(studentList);
        

        //counts number of non blank student indexes to be used as a condition in for loops
        studentsInList = Student.arrayEntryLength(studentList);
                
        do{
            
            System.out.println("Welcome to the Student Information program ");
            System.out.println("Enter 1 to quit");
            System.out.println("Enter 2 to enter student information");
            System.out.println("Enter 3 to display information of all students");
            System.out.println("Enter 4 to display average mark for Undergrads or Postgrads");
            System.out.println("Enter 5 to display how many students obtained marks equal to and above or below the average");
            System.out.println("Enter 6 to enter a student ID number and display the students information");
            System.out.println("Enter 7 to enter a students name and display the students information");
            System.out.println("Enter 8 to sort the array of students by ascending student number");
            System.out.println("Enter 9 to output the student array to a CSV file");

            // gets menu choice from user, includes exception handling for non Int entered
            do{
                System.out.println("Enter a number from 1 to 9");
                menu = Student.getInt();   
            } while(menu < 0 || menu > 9);
            
            System.out.print("\n");
            switch (menu)
            {
                case 1:
                    quit();
                    break;

                case 2:
                    setStudentMarks();    
                    break;                    

                case 3:
                    displayStudentInfo();
                    break;

                case 4:
                    displayOverallMark();
                    break;

                case 5:
                    displayAboveAverage();
                    break;

                case 6:
                    findByStudentID();
                    break;

                case 7:
                    findByName();    
                    break;                    

                case 8:
                    sortByStudentID();
                    break;

                case 9:
                    outputToCSV();
                    break;

                default:
                    System.out.println("Enter a number from 1 to 9");
                    break;
            }
        }while(menu != 1);
    }
    
    public static void quit(){
        System.out.println("Quitting..");
        //System.exit(0);
    }
    
    /**
     * Gets user to enter in the students marks for either UnderGrad or PostGrad
     * Computes and sets the students final mark and grade
     */
    // REAL SET STUDENT DONT DELete
    public static void setStudentMarks(){  //
        char choice = 'q';
        int index = 0;
        
        System.out.println("This option lets you add marks to students");
        
        for(int i = 0; i < studentsInList; i++){

            System.out.println("\nStudent Number: " + (i+1));
            System.out.println(studentList[i].toString());      //Prints students already loaded information to screen to check user has correct student
            studentList[i].setMarks();                          //Gets user to enter marks for UnderGrad or PostGrad
            studentList[i].computeMark();                       //sets Final mark and grade for student
        }
    }

    
    /**
     * Uses to toString from students assigned class to print out students information and grades
     */ 
    public static void displayStudentInfo(){
        System.out.println("Displaying student stored student information\n");
        
        for(int i = 0; i < studentsInList; i++){
            System.out.println(studentList[i].toString());
        }
    }
    
    
    /**
     * Displays average final mark for UnderGrads and PostGrads
     * Gets user to choose Undergrad or Postgrad
     * Calls method from chosen grad type to calculate the average grade for all the grad types loaded in students
     * 
     * Expects setStudentMarks() method to be run first - grades need to be entered for students first
     */
    public static void displayOverallMark(){
        String studentType = " UnderGraduate";  // set to Postgrad if user chooses 'p'
        double averageMarks = 0;
        System.out.println("This option displays the average mark for all Undergraduates or Postgraduates");  
        System.out.println("Display Undergraduate or Postgraduate students Average mark");        
        char choice = selectStudentType();      //gets user to choose grad type
        
        if (choice == 'u' )
            averageMarks = UnderGrad.getStudentsAverage(studentList);   //sends array to UnderGrad, computes UnderGrad average from undergrads found in array
        else{
            averageMarks = PostGrad.getStudentsAverage(studentList);    //as above for Post grads
            studentType = "PostGraduate";
        }
        
        //If all final marks found == 0, marks havent been enterred in yet, making this method useless
        if(averageMarks == 0)
            System.out.println("Computing average mark failed. Enter students marks first");    
        else
            System.out.println(studentType + " Students average mark is: " + averageMarks + "\n"); 
    }
    
    
    /**
     * Displays number of students greater/equal to average, or below average
     * Gets user to choose UnderGrad or PostGrad
     * Gets average for chosen student type
     * Compares each student from selected type against type average, counts number of greater/equal students
     * Displays number of greater/equal students, displays the rest as below average
     * 
     * Expects setStudentMarks() method to be run first - grades need to be entered for students first
     */ 
    public static void displayAboveAverage(){
        
        System.out.println("This option displays Undergraduate or Postgraduate students Average mark");
        
        char choice = selectStudentType();
        if(choice == 'u')
            UnderGrad.aboveAverage(studentList);
        else
            PostGrad.aboveAverage(studentList);            
    }       
    
    /**
     * Find if a user entered student number is in the student list
     * Calls method in Student class to get user input and returns index if found
     * Prints the found student to screen
     */
    public static void findByStudentID(){
        int index = Student.findByID(studentList);  // index of student with same studentID entered by user
        
        if(index != -1)
            System.out.println(studentList[index].toString());  // prints the found users info
    }
    
    /**
     * Find if a user entered student first and last name are in the student list
     * Calls method in Student class to get user input and returns index if found
     * Prints the found student to screen
     */
    public static void findByName(){
        int index = Student.findByName(studentList);    // index of student with same first and last names, entered by user
        
        if(index != -1)
            System.out.println(studentList[index].toString());
    }
    
    /**
     * Sorts array of students from lowest studentID number to highest
     * Prints sorted list to screen
     */
    public static void sortByStudentID(){
        studentList = Student.arraySort(studentList);
        
        System.out.println("List of students now sorted by ascending student ID\n");
        
        for(int i = 0; i < studentsInList; i++){
            System.out.println(studentList[i].toString());
        }
    }
    
    /**
     * Prints list of students into CSV file for use in Excel
     * Can output blank student indexes in array
     * Can output students with no marks entered
     */
    public static void outputToCSV(){
        Student.printToCSV(studentList);  
    }
    
    /**
     * Gets the user to choose either under or post grad
     * @return Returns the users choice of under or post grad with chars 'u' or 'p'
     */
    public static char selectStudentType(){
        char choice = 'q';
        while(choice != 'u' && choice != 'p'){
            System.out.println("Enter 'u' for undergrad and 'p' for postgrad");
            choice = keyboard.next().toLowerCase().charAt(0);
        }
        System.out.print("\n");
        return choice;
    }
      

    /**
     * My Student information printed to screen
     */
    public static void studentInfo(){
        System.out.println("Name: Michael John");
        System.out.println("Student Number: 33478628");
        System.out.println("Enrolment: External\n");
    }    
    
}
