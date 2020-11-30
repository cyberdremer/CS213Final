package sample;

import java.io.Serializable;

public class Course implements Serializable {
    private int  CourseNumber;
    private String InstructorName, CourseName, DepartmentName, CourseID;
    Course(){
        CourseNumber = 0;
        CourseID = "";
        InstructorName = "";
        CourseName = "";
        DepartmentName = "";
    }

    Course( int CourseNumber, String InstructorName, String CourseName, String DepartmentName, String CourseID){
        this.CourseID = CourseID;
        this.CourseNumber = CourseNumber;
        this.InstructorName = InstructorName;
        this.CourseName = CourseName;
        this.DepartmentName =DepartmentName;


    }

    public void setCourseNumber(int CourseNumber) {

        this.CourseNumber = CourseNumber;
    }

    public void setInstructorName(String instructorName) {

        this.InstructorName = instructorName;

    }
    public void setDepartmentName(String DepartmentName){
        this.DepartmentName = DepartmentName;

    }

    public void setCourseName(String CourseName){
        this.CourseName = CourseName;

    }
    public void setCourseID(String CourseID){
        this.CourseID = CourseID;
    }

    public int getCourseNumber(){
        return CourseNumber;
    }

    public String getInstructorName(){
        return InstructorName;
    }

    public String getCourseName(){
        return CourseName;
    }

    public String getDepartmentName(){
        return DepartmentName;
    }

    public String getCourseID(){
        return CourseID;
    }

    public String toString(){
        return  "\nCourse ID: " + this.getCourseID() + "\nCourse Name: " + this.getCourseName() +
                "\nCourse Number: " + this.getCourseNumber() + "\nInstructor Name: " + this.getInstructorName() + "\nDepartment Name: "
                + this.getDepartmentName()+"\n";
    }



}
