/**
 * Student Class
 * Michael John
 * 19/10/2019
 * Student.java
 * 
 * The student class contains methods to read student information into Student
 * Objects. From there, methods can be used to give marks to students, find 
 * students average marks and a comparison of students to the average mark. The
 * class also contains methods to find student objects by name or id number.
 * Lastly it includes methods to get student information from a .txt file and 
 * output the students to a .csv file
 * 
 * 
 *
 * @author Michael John
 * @version 1.0
 * @since 19/10/2019
 */

package assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.lang.String;
import java.util.*;
import java.io.IOException;

public class Student {
    private String title;
    private String firstName;
    private String lastName;
    private long studentID;
    private int dayDOB;
    private int monthDOB;
    private int yearDOB;
    private double finalMark;
    private String grade;

    static Scanner keyboard = new Scanner(System.in);
    
    /**
     * Base constructor that sets a student object with no information
     */
    Student(){
        title = "No title set";
        firstName = "No first name set";
        lastName = "No last name set";
        studentID = 0;
        dayDOB = 0;
        monthDOB = 0;
        yearDOB = 0;
        finalMark = 0;
    }
    
    /**
     * Constructor that sets Student object with passed parameters
     * @param setTitle  String - Students Title
     * @param first String - Students Title
     * @param last String - Students Title
     * @param number long - Students studentID
     * @param day Integer - Students day of birth
     * @param month Integer - Students month of birth
     * @param year Integer - Students year of birth
     */
    Student(String setTitle, String first, String last, long number, int day, int month, int year){
        title = setTitle;
        firstName = first;
        lastName = last;
        studentID = number;
        dayDOB = day;
        monthDOB = month;
        yearDOB = year;  
        finalMark = 0;
    }
    
    /**
     * Sets the students title as a string
     * No checking whether the title is genuine
     */
    public void setTitle(){
        System.out.println("Enter the students title (eg Mr, Miss, Ms, Mrs etc)");
        title = keyboard.nextLine();   
    }

    /**
     * Sets the students first and last name as strings
     * No checking whether the names are genuine
     * Names are set with capitals as entered
     * Equals methods will need to include toLower() for comparisons
     */    
    public void setName(){
        System.out.println("Enter the students first name");
        firstName = keyboard.nextLine();
        System.out.println("Enter the students last name");
        lastName = keyboard.nextLine();
    }
    
    /**
     * Sets the students Student ID number as a long
     * No checking whether the entered long will work
     */      
    public void setStudentID(){
        System.out.println("Enter the students ID number");
        studentID = keyboard.nextLong();
    }
    
    
    /**
     * Sets the students date of birth as 3 ints - the day, the month and the year
     * Checks whether the entered ints can fit within dating parameters
     */       
    public void setStudentDOB(){
        System.out.println("Enter the students date of birth (ddmmyyyy)");
        
        while(dayDOB < 1 || dayDOB > 31){
            System.out.println("Enter the day <dd>");
            dayDOB = getInt();              
        }  
        while(monthDOB < 1 || monthDOB > 12){
            System.out.println("Enter the month <mm>");
            monthDOB = getInt();              
        }
        while(yearDOB < 1901 || yearDOB > 2100){
            System.out.println("Enter the year <yyyy>");
            yearDOB = getInt();              
        }            
    }
    
    
    /**
     * Sets all student base information excluding all marks and grades
     * Includes setTitle(), setName(), setStudentID() and setStudentDOB()
     */
    public void setStudentAll(){
        setTitle();
        setName();
        setStudentID();
        setStudentDOB();        
    }    
    
    /**
     * Print all student information to screen including final mark and grade
     * Excludes individual assessment marks
     * @return Returns all student information printed to screen, formatted with titles
     * @Override Overrides Object toString(), overridden by derived classes
     */
    public String toString(){
        return "Title: "+ title + 
        "\nName: "+ firstName + " " + lastName +
        "\nStudent ID: " + studentID +
        "\nStudent DOB: " + dayDOB + " " + monthDOB + " " + yearDOB +
        "\nFinal Mark: " + finalMark + "%" +
        "\nFinal Grade: " + grade + "\n";
    }
    
    /**
     * Accessor to get private variable  - String title
     * @return Returns String title
     */    
    public String getTitle(){
        return title;
    }    
    
    /**
     * Accessor to get private variable  - long studentID
     * @return Returns long studentID
     */
    public long getStudentID(){
        return studentID;
    }

    /**
     * Accessor to get private variable  - String firstName
     * @return Returns String firstName
     */    
    public String getFirstName(){
        return firstName;
    }
    
    /**
     * Accessor to get private variable  - String lastName
     * @return Returns String lastName
     */       
    public String getLastName(){
        return lastName;
    }
    
    /**
     * Accessor to get private variables  - Int dayDOB
     * @return Returns Int dayDOB
     */
    public int getDayDOB(){
        return dayDOB;
    }    
    
    /**
     * Accessor to get private variables  - Int monthDOB
     * @return Returns Int monthDOB
     */
    public int getMonthDOB(){
        return monthDOB;
    }  
    
    /**
     * Accessor to get private variables  - Int yearDOB
     * @return Returns Int yearDOB
     */
    public int getYearDOB(){
        return yearDOB;
    }      

    /**
     * Sets students grade as compared to students final mark
     * Given students final mark as a parameter and compares to grade definitions
     * Requires a double parameter which should be the students final mark
     */        
    public void setGrade(double mark){
        finalMark = mark;
        
        if(finalMark == 0)
            grade = "No Final Mark entered";
        else
            if(finalMark < 50)
                grade = "N";
            else
                if(finalMark < 60)
                    grade = "P";
                else
                    if(finalMark < 70)
                        grade = "C";
                    else
                        if(finalMark < 80)
                            grade = "B";
                        else
                            grade = "A";
    }    
    
    /**
     * Computes and sets this students Final mark
     * Computes this students scores for assessments then computes assessment contributions to final mark
     * Requires student to have marks entered previously
     * Overridden in derived classes
     */    
    public void computeMark(){
        setGrade(finalMark);
    }

    /**
     * Accessor to get this students Final Mark
     * Requires marks entered for assessment items
     * @return Returns this students double finalMark
     */          
    public double getMark(){
        return finalMark;
    }
    
    /**
     * Accessor to get this students grade
     * Requires marks entered for assessment items
     * @return Returns this students String grade
     */          
    public String getGrade(){
        return grade;
    }    

    
    /**
     * Calls methods to set marks for all of this students assessments
     * Overridden in derived classes
     */    
    public void setMarks(){
        System.out.println("Student is not enrolled in an Under or Post Graduateship");
    }
    
    /**
     * Compares Student objects and returns true if they are equal
     * Compares first and last name, converts name to lower case to compare
     * Compares the 3 Date of birth Ints
     * @param otherStudent Other student to compare if equal to this student
     * @return True if this student equals other student, otherwise false
     */
    public boolean equals(Student otherStudent){
        boolean sameFirstName, sameLastName, sameStudentDOB;
        sameFirstName = this.firstName.equalsIgnoreCase(otherStudent.firstName);
        sameLastName = this.lastName.equalsIgnoreCase(otherStudent.lastName);
        sameStudentDOB = (this.dayDOB == otherStudent.dayDOB) && (this.monthDOB == otherStudent.monthDOB) && (this.yearDOB == otherStudent.yearDOB);
        
        return(sameFirstName && sameLastName && sameStudentDOB);
    }
    
    
    /**
     * Sorts array of student by StudentID in ascending order
     * Only sorts parameter arrayTOSort array up to index with no entered studentID number to avoid sorting indexes with no information
     * @param arrayToSort array of Students client wishes to sort
     * @return arrayToSort, now sorted by ascending studentID numbers
     */    
    public static Student[] arraySort(Student[] arrayToSort){
        int i, j;
        for (i = 1; i < Student.arrayEntryLength(arrayToSort); i++){
            for (j = 0; j < Student.arrayEntryLength(arrayToSort) - 1; j++){
                if(arrayToSort[j].studentID > arrayToSort[j + 1].studentID){
                    swap(arrayToSort, j, j + 1);                    //Swaps indexes if next index has lower studentID long than current index
                }
            }        
        }
        return arrayToSort;
    }
    
    /**
     * Swap Method used by arraySort() to swap indexes if next index studentID is < than current
     * @param array Array being sorted
     * @param first Current index
     * @param second Next index
     */
    private static void swap(Student[] array, int first, int second){
        Student temp;
        temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }    
    
    /**
     * Finds a student by studentID number
     * Gets user to enter a long, searches each index and compares users long to studentID long
     * Returns the index if users long matches with a studentID long
     * Only sorts parameter arrayTOSort array up to index with no entered studentID number to avoid sorting indexes with no information
     * @param array Array to search for users long with index studentID
     * @return Returns Int with index of matching studentID or returns -1 for not found
     */
    public static int findByID(Student[] array){
        int index = -1;
        
        System.out.println("Enter the Student ID number of the student");
        long studentID = keyboard.nextLong();
        for(int i = 0; i < array.length; i++){
            if(studentID == array[i].getStudentID()){
                index = i;
                break;
            }
        }
        if(index == -1)     //returns -1 for client to use if index not found
            System.out.println("The student ID you entered was not found\n");   
        return index;
    }
    
    /**
     * Finds a student by first and last names
     * Gets user to enter Strings for first and last name, searches each index and compares users Strings to firstName and lastName Strings
     * Returns the index if users Strings match with index firstName and lastName Strings
     * Only sorts parameter arrayTOSort array up to index with no entered studentID number to avoid sorting indexes with no information
     * @param array Array to search for users firstName and lastName Strings with index 
     * @return Returns Int with index of matching studentID or returns -1 for not found
     */
    public static int findByName(Student[] array){
        int index = -1;
        System.out.println("Enter the name of the student to display their information");
        System.out.println("Enter the first name - Not case sensitive");
        String first = keyboard.next().toLowerCase();
        
        System.out.println("Enter the last name - Not case sensitive");
        String last = keyboard.next().toLowerCase();
        
        for(int i = 0; i < arrayEntryLength(array); i++){
            if(first.toLowerCase().equals(array[i].getFirstName()) ){
                System.out.println("First name matches");
                if(last.toLowerCase().equals(array[i].getLastName())){
                    System.out.println("Last name matches");
                    index = i;
                    array[i].toString();
                    break;
                }
                System.out.println("Last name did not match for student" + (i+1));  //Lets user know if any part of name matches
            }
            else
                System.out.println("First name did not match for student" + (i+1)); //As above
        }
        if(index == -1)     //returns -1 for client to use if index not found
            System.out.println("The names you entered were not found\n");  
        
        return index;
    }

    
    /**
     * Prints average of Final marks for students
     * Expects student marks to be entered
     * Overridden by derived classes
     * Contains exception handling for divide by 0 if no student marks entered yet  
     * @param array Array of Students to compute the combined average mark
     * @return Returns the students combined average as a double
     */
    public static double getStudentsAverage(Student[] array){
        double marksTotal = 0;
        int studentsCount = 0;
        
        for(int i = 0; i < array.length; i++){  //doesnt check run for loop until studentID == 0, as all Student objects will have studentID == 0
                studentsCount++;
                marksTotal = marksTotal + array[i].finalMark; //passes mark from the students to the grade counting method 
        }
        
        //prevents division by 0 which will always happen as no student marks have been entered
        try{
            if(studentsCount == 0)
                throw new Exception("Can not divide by 0");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("No student information entered");
            System.out.println("Input student data before choosing students average mark");
            return(0);
        }
        return marksTotal / studentsCount;
    }    
    
    
    /**
     * Compares student's finalMark with the class average
     * Won't work with student class but is to be overridden by derived classes
     * @param array The array of students to compare to the average
     */
    public static void aboveAverage(Student[] array){
        double average = getStudentsAverage(array);
        int aboveAverageCount = 0;
        
        if(average != 0){
            for(int i = 0; i < arrayEntryLength(array); i++){
                if(array[i].finalMark >= average)
                    aboveAverageCount++;
            }
            
        System.out.println(aboveAverageCount + " students scored equal to or above average");
        System.out.println(arrayEntryLength(array) - aboveAverageCount + " students scored below average\n");
        }
        else
            System.out.println("Computing average mark failed. Enter students marks first");   
    }    
    
    /**
     * Loads all student information except for marks from Student.txt
     * Checks if opening file is successful, will exit program if unable to open
     * @param array Array to load student information into
     */
    public static void loadFile(Student[] array){
        String studentType = null;
        String setTitle = null;;
        String first = null;
        String last = null;
        long iDNumber = 0;
        int day, month, year; 
        int i = 0;
        
        String txtFileName = "student.txt";
        Scanner inputStream = null;
        
        try{
            inputStream = new Scanner(new File(txtFileName));
        }
        catch(FileNotFoundException e){
            System.out.println("Error opening the file" + txtFileName);
            System.exit(0);
        }
        System.out.println("Reading from file " + txtFileName);
        
        // reads from file into variables
        while(inputStream.hasNextLine()){
            studentType = inputStream.next();
            setTitle = inputStream.next();
            first = inputStream.next();
            last = inputStream.next();
            iDNumber = inputStream.nextLong();
            day = inputStream.nextInt();
            month = inputStream.nextInt();
            year = inputStream.nextInt();
            
            //checks if student is under or post grad, loads student info into index from variables
            if(studentType.toLowerCase().charAt(0) == 'u'){
                array[i] = new UnderGrad(setTitle, first, last, iDNumber, day, month, year);
                System.out.println("Undergraduate " + (i + 1) + " Entered");
                i++;
            }
            
            if(studentType.toLowerCase().charAt(0) == 'p'){
                array[i] = new PostGrad(setTitle, first, last, iDNumber, day, month, year);
                System.out.println("PostGraduate " + (i + 1) + " Entered");
                i++;
            }                                   
        }
        
        System.out.println("Finished reading from file");
        inputStream.close();
        System.out.println(txtFileName + " closed");
    }
    
    /**
     * Helper method to arrange student information into CSV format
     * @return String of students information in CSV format
     */
    private String toCSV(){
        return(title + "," + firstName + "," + studentID + "," + dayDOB + monthDOB + yearDOB + "," + finalMark + "," + grade);
    }
    
    /**
     * Outputs student information into Students.CSV
     * Contains exception handling if Students.CSV unable to be opened
     * @param array Array to output all student indexes into a CSV file
     */
    public static void printToCSV(Student array[]){
        String csvFileName = "students.csv";
        PrintWriter outputStream = null;
        
        try{
            outputStream = new PrintWriter(csvFileName);
        }
        catch(FileNotFoundException e){
            System.out.println("Error opening file " + csvFileName);
            System.exit(0);
        }
        
        System.out.println(csvFileName + " Opened successfully\n");
        
        //uses toCSV to arrange student information in CSV format
        for(int i = 0; i < Student.arrayEntryLength(array); i++){
            outputStream.println(array[i].toCSV());
        }
        System.out.println("Students entered into CSV file");
        outputStream.close();
        System.out.println(csvFileName + " closed\n");        
    }
    /**
     * Counts number of indexes in array with student information loaded into array
     * Counts until first index with studentID == 0
     * used for limits to for loops so indexes with no information are used
     * @param array Array to count the number of students loaded into
     * @return Int with number of indexes student information loaded into array
     */
    public static int arrayEntryLength(Student[] array){
        int entryCount = 0;
                
        for(int i = 0; i < array.length; i++)
            if(array[i].studentID != 0)
                entryCount++;      
        return entryCount;
    }

    
    /**
     * Exception Handling method to prevent fault at menu choice if user enters anything but an Int
     * @return Returns an Int
     */    
    public static int getInt(){
            boolean intEntered = true;
            int input = -1;

            try{
                input = keyboard.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("Warning: This input requires an integer to be entered");
                keyboard.next();
            }
            
            return input;
    }    
    
    
    /**
     * Exception Handling method to prevent fault at menu choice if user enters anything but an Double
     * @return Returns a Double
     */        
    public static double getDouble(){
            double input = -1;

            try{
                input = keyboard.nextDouble();
            }
            catch(InputMismatchException e){
                System.out.println("Warning: This input requires an double to be entered");
                keyboard.next();
            }
            
            return input;
    }     
}// end of class