package sample;

public class Enrollment  {
    private int CourseID, StudentID, Year;
    private String Semester, Grade, CourseNumber, CourseName;

    Enrollment(){
        CourseID = 0;
        StudentID = 0;
        Grade =" ";
        Year = 0;
        Semester = "";

    }

    Enrollment(int CourseID, int StudentID, int Year, String Semester, String Grade, String CourseNumber, String CourseName){
        this.CourseID = CourseID;
        this.StudentID = StudentID;
        this.Year = Year;
        this.Semester = Semester.toUpperCase();
        this.Grade = Grade.toUpperCase();
        this.CourseNumber = CourseNumber.toUpperCase();
        this.CourseName = CourseName;

    }

    public void setGrade(String Grade){

        this.Grade = Grade;
    }
    public void setCourseID(int CourseID){

        this.CourseID = CourseID;

    }
    public void setStudentID(int StudentID){
        this.StudentID = StudentID;

    }
    public void setYear(int Year) {
        this.Year = Year;
    }

    public void setSemester(String Semester)  {
        this.Semester = Semester;
    }

    public int getYear(){
        return Year;
    }

    public String getSemester(){
        return  Semester;
    }

    public int getCourseID(){
        return CourseID;
    }

    public int getStudentID(){
        return StudentID;
    }

    public String getGrade(){
        return Grade;
    }

    public String getCourseNumber(){
        return CourseNumber;
    }

    public void setCourseNumber(String CourseNumber) {
        this.CourseNumber = CourseNumber;
    }
    public String getCourseName(){
        return CourseName;
    }

    public void setCourseName(String CourseNumber){

        this.CourseName = CourseName;
    }








}
