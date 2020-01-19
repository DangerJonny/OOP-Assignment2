/**
 * PostGrad Class
 * Michael John
 * 19/10/2019
 * PostGrad.java
 * 
 * PostGrad.class is derived from the Student.Class. It has new variables to 
 * allow getting marks from the user and computing the unit final mark for 
 * PostGraduate students.
 * 
 *
 * @author Michael John
 * @version 1.0
 * @since 19/10/2019
 */
package assignment2;

import java.util.Scanner;


class PostGrad extends Student{
    
    double groupAssignment;
    double groupPresentation;
    double finalExam;
    final static int MAX_GROUPASSIGNMENT_SCORE = 100;
    final static int MAX_GROUPPRESENTATION_SCORE = 100;
    final static int MAX_FINALEXAM_MARKS = 100;
    //weights of how much assessments contribute to finalMark
    final static int GROUPASSIGNMENT_WEIGHT = 30;
    final static int GROUPPRESENTATION_WEIGHT = 20;
    final static int FINALEXAM_WEIGHT = 50;

    Scanner keyboard = new Scanner(System.in);
    
    /**
     * Overrides Student Class base constructor
     */
    PostGrad(){
        super();
    }
    
    /**
     * Overrides Student Class constructor that sets PostGrad object with passed parameters
     * @param setTitle  String - Students Title
     * @param first String - Students Title
     * @param last String - Students Title
     * @param number long - Students studentID
     * @param day Integer - Students day of birth
     * @param month Integer - Students month of birth
     * @param year Integer - Students year of birth
     */    
    PostGrad(String setTitle, String first, String last, long number, int day, int month, int year){
        super(setTitle, first, last, number, day, month, year); 
    }
    
    
    /**
     * Mutator to set this PostGraduates group assignment score
     * Contains error checking to ensure score entered is within allowable range
     */
    public void setGroupAssignment(){
        do{
            System.out.println("Enter the mark for the Assignment (0-100)");
            groupAssignment = Student.getInt();
        } while (groupAssignment < 0 || groupAssignment > MAX_GROUPASSIGNMENT_SCORE);
    }
    
    /**
     * Mutator to set this PostGraduates group presentation score
     * Contains error checking to ensure score entered is within allowable range
     */    
    public void setGroupPresentation(){
        do{
            System.out.println("Enter the mark for the Group Presentation (0-100)");
             groupPresentation = Student.getInt();           
        } while (groupPresentation < 0 || groupPresentation > MAX_GROUPPRESENTATION_SCORE);   
    }

    /**
     * Mutator to set this PostGraduates group final exam score
     * Contains error checking to ensure score entered is within allowable range
     */    
    public void setFinalExam(){
        do{
            System.out.println("Enter the mark for the Final Exam (0-100)");
            finalExam = Student.getInt();           
        } while (finalExam < 0 || finalExam > MAX_FINALEXAM_MARKS);    
    }    
    
    /**
     * Calls methods to set marks for all of this PostGrads assessments
     * Includes setGroupAssignment(), setGroupPresentation() and setFinalExam()
     */
    @Override
    public void setMarks(){
        setGroupAssignment();
        setGroupPresentation();
        setFinalExam(); 
    }  
    
    
    /**
     * Sets all marks for this student - used for testing
     * Expects students marks for assessments passed as parameters
     * No error checking provided
     * @param assignment Double - assignment score
     * @param presentation Double - presentation score
     * @param exam Double - exam score
     */
    public void setMarks(double Assignment, double Presentation, double Exam){           //testing!!!!
        groupAssignment = Assignment;
        groupPresentation = Presentation;
        finalExam = Exam;
    }        
    
    /**
     * Computes and sets this students Final mark
     * Computes this PostGrads scores for assessments then computes assessment contributions to final mark
     * Requires PostGrad to have marks entered previously
     * Uses Student.setGrade(overallMark) to get PostGrads grade
     * Overrides ancestor Student Class method
     */  
    @Override
    public void computeMark(){
        double overallMark;
        
        overallMark = (groupAssignment / 100) * GROUPASSIGNMENT_WEIGHT;
        overallMark = overallMark + (groupPresentation / 100) * GROUPPRESENTATION_WEIGHT;
        overallMark = overallMark + (finalExam / MAX_FINALEXAM_MARKS) * FINALEXAM_WEIGHT;

        setGrade(overallMark);
    }

    /**
     * Prints average of Final marks for students
     * Expects PostGrads marks to be entered
     * Overrides by ancestor Student class
     * Contains exception handling for divide by 0 if no student marks entered yet  
     * @param array Array of Students find PostGrads to compute the combined average mark
     * @return Returns the PostGrads combined average as a double
     */
    public static double getStudentsAverage(Student[] array){
        double marksTotal = 0;
        int studentsCount = 0;
        
        //checks whole array for Postgrad objects and combines total of finalMarks
        for(int i = 0; i < array.length; i++){
            if(array[i] instanceof PostGrad){    
                studentsCount++;
                marksTotal = marksTotal + array[i].getMark(); //uses ancestor Student.getMark() to get private finalMark
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
     * Compares PostGrad's finalMark with the class average
     * Overrides ancestor class
     * @param array The array of students to get PostGrad's from and compare to the PostGrad average
     */
    public static void aboveAverage(Student[] array){
        double average = PostGrad.getStudentsAverage(array);
        int postGradsCount = 0;
        int aboveAverageCount = 0;
        
        if(average != 0){
            for(int i = 0; i < arrayEntryLength(array); i++){
                if(array[i] instanceof PostGrad){
                    postGradsCount++;
                    if(array[i].getMark() >= average)
                        aboveAverageCount++;
                }
            }
            
        System.out.println(aboveAverageCount + " Postgraduates scored equal to or above average");
        System.out.println(postGradsCount - aboveAverageCount + " Postgraduates scored below average\n");
        }
        else
            System.out.println("Computing average mark failed. Enter students marks first\n");   
    }  

    /**
     * Print all PostGrad information to screen including final mark and grade
     * Excludes individual assessment marks
     * @return Returns all student information printed to screen, formatted with titles
     * @Override Overrides Student toString()
     */
    @Override
    public String toString(){
        return "PostGraduate\n" + super.toString();
    }   
}
