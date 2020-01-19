/**
 * UnderGrad Class
 * Michael John
 * 19/10/2019
 * UnderGrad.java
 * 
 * UnderGrad.class is derived from the Student.Class. It has new variables to 
 * allow getting marks from the user and computing the unit final mark for 
 * UnderGraduate students.
 * 
 * @author Michael John
 * @version 1.0
 * @since 19/10/2019
 */
package assignment2;

import java.util.Scanner;


public class UnderGrad extends Student{
    double assignment1;
    double assignment2;
    double assignment3;
    double weeklyPrac;
    double finalExam;
    final static double MAX_ASSIGNMENT_MARKS = 100;
    final static double MAX_WEEKLYPRAC_MARKS = 10;
    final static double MAX_FINALEXAM_MARKS = 100;
    //weights of how much assessments contribute to finalMark
    final static double ASSIGNMENT_WEIGHT = 15;
    final static double WEEKLYPRAC_WEIGHT = 10;
    final static double FINALEXAM_WEIGHT = 45;

    
    Scanner keyboard = new Scanner(System.in);

    /**
     * Overrides Student Class base constructor
     */      
    UnderGrad(){
        super();
    }
    
    /**
     * Overrides Student Class constructor that sets UnderGrad object with passed parameters
     * @param setTitle  String - Students Title
     * @param first String - Students Title
     * @param last String - Students Title
     * @param number long - Students studentID
     * @param day Integer - Students day of birth
     * @param month Integer - Students month of birth
     * @param year Integer - Students year of birth
     */      
    UnderGrad(String setTitle, String first, String last, long number, int day, int month, int year){
        super(setTitle, first, last, number, day, month, year); 
    }
    

    /**
     * Mutator to set all of this UnderGraduates assignment scores
     * Contains error checking to ensure score entered is within allowable range
     */
    public void setAssignments(){
        do{
            System.out.println("Enter the mark for Assignment 1 (0-100)");
            assignment1 = Student.getInt();
        } while (assignment1 < 0 || assignment1 > MAX_ASSIGNMENT_MARKS);
        
        do{
            System.out.println("Enter the mark for Assignment 2 (0-100)");
            assignment2 = Student.getInt();
        } while (assignment2 < 0 || assignment2 > MAX_ASSIGNMENT_MARKS);
        
        do{
            System.out.println("Enter the mark for Assignment 3 (0-100)");
            assignment3 = Student.getInt(); 
        } while (assignment3 < 0 || assignment3 > MAX_ASSIGNMENT_MARKS);
    }

    /**
     * Mutator to set this UnderGraduates weeklyPrac score
     * Contains error checking to ensure score entered is within allowable range
     */    
    public void setWeeklyPrac(){
        do{
            System.out.println("Enter the mark for the weekly practical work (0-10)");
            weeklyPrac = Student.getInt();           
        } while (weeklyPrac < 0 || weeklyPrac > MAX_WEEKLYPRAC_MARKS);   
    }

    /**
     * Mutator to set this UnderGraduates finalExam score
     * Contains error checking to ensure score entered is within allowable range
     */    
    public void setFinalExam(){
        do{
            System.out.println("Enter the mark for the final exam (0-100)");
            finalExam = Student.getInt();           
        } while (finalExam < 0 || finalExam > MAX_FINALEXAM_MARKS);    
    }    

    /**
     * Calls methods to set marks for all of this UnderGrads assessments
     * Includes setAssignments(), setWeeklyPrac() and setFinalExam()
     */    
    @Override
    public void setMarks(){
        setAssignments();
        setWeeklyPrac();
        setFinalExam();  
    }    
 

    /**
     * Sets all marks for this student - used for testing
     * Expects students marks for assessments passed as parameters
     * No error checking provided
     * @param assign1 Double - assign1 score
     * @param assign2 Double - assign2 score
     * @param assign3 Double - assign3 score
     * @param weekly Double - weeklyPrac score
     * @param finalEx Double - FinalExam score
     */
    public void setMarks(double assign1, double assign2, double assign3, double weekly, double finalEx){
        assignment1 = assign1;
        assignment2 = assign2;
        assignment3 = assign3;
        weeklyPrac = weekly;
        finalExam = finalEx;
    }    
    
    /**
     * Computes and sets this students Final mark
     * Computes this UnderGrads scores for assessments then computes assessment contributions to final mark
     * Requires UnderGrad to have marks entered previously
     * Uses Student.setGrade(finalMark) to get UnderGrads grade
     * Overrides ancestor Student Class method
     */    
    @Override
    public void computeMark(){
        
        double finalMark;

        finalMark = ((assignment1 / MAX_ASSIGNMENT_MARKS) + (assignment2 / MAX_ASSIGNMENT_MARKS) +
                        (assignment3 / MAX_ASSIGNMENT_MARKS)) * ASSIGNMENT_WEIGHT;
        finalMark = finalMark + ((weeklyPrac / MAX_WEEKLYPRAC_MARKS) * WEEKLYPRAC_WEIGHT);
        finalMark = finalMark + ((finalExam / MAX_FINALEXAM_MARKS) * FINALEXAM_WEIGHT);

        setGrade(finalMark);
    }    

    /**
     * Prints average of Final marks for students
     * Expects UnderGrads marks to be entered
     * Overrides by ancestor Student class
     * Contains exception handling for divide by 0 if no student marks entered yet  
     * @param array Array of Students to find UnderGrads and compute the combined average mark
     * @return Returns the UnderGrads combined average as a double
     */ 
    public static double getStudentsAverage(Student[] array){
        double marksTotal = 0;
        int studentsCount = 0;
        
        //checks whole array for UnderGrad objects and combines total of finalMarks
        for(int i = 0; i < array.length; i++){
            if(array[i] instanceof UnderGrad){    
                studentsCount++;
                marksTotal = marksTotal + array[i].getMark(); //passes mark from the students to the grade counting method
            }
        }
        
        //prevents division by 0 which will always happen as no student marks have been entered
        try{
            if(studentsCount == 0)
                throw new Exception("Can not divide by 0");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("No student overall marks entered");
            System.out.println("Input student data before choosing students average mark");
            return(0);
        }
        
        return marksTotal / studentsCount;
    }        
    
    
    /**
     * Compares UnderGrad's finalMark with the class average
     * Overrides ancestor class
     * @param array The array of students to get UnderGrad's from and compare to the UnderGrad average
     */
    public static void aboveAverage(Student[] array){
        double average = UnderGrad.getStudentsAverage(array);
        int underGradsCount = 0;
        int aboveAverageCount = 0;
        
        if(average != 0){
            for(int i = 0; i < arrayEntryLength(array); i++){
                if(array[i] instanceof UnderGrad){
                    underGradsCount++;
                    if(array[i].getMark() >= average)
                        aboveAverageCount++;
                }
            }
            
        System.out.println(aboveAverageCount + " Undergraduates scored equal to or above average");
        System.out.println(underGradsCount - aboveAverageCount + " Undergraduates scored below average\n");
        }
        else
            System.out.println("Computing average mark failed. Enter students marks first\n");   
    }  
     
    /**
     * Print all UnderGrad information to screen including final mark and grade
     * Excludes individual assessment marks
     * @return Returns all student information printed to screen, formatted with titles
     * @Override Overrides Student toString()
     */    
    @Override
    public String toString(){
        return "UnderGraduate\n" + super.toString();
    }     
}
