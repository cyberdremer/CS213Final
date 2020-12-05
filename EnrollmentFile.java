package sample;

import java.io.IOException;
import java.io.RandomAccessFile;

public class EnrollmentFile {
    private RandomAccessFile courseEnrollmentFile;
    private long RECORD_SIZE = 262;

    EnrollmentFile(String filename) throws IOException {
        courseEnrollmentFile = new RandomAccessFile(filename, "rw");

    }


    public void writeCourseItem(Enrollment ce) throws IOException{
        String str = ce.getSemester();
        String str2 = ce.getGrade();
        String str3 = ce.getCourseNumber();
        String str4 = ce.getCourseName();
        String str5 = ce.getStudentName();
        String[] courseInformation = {str,str2,str3, str4, str5};


        for(int i = 0; i < courseInformation.length; i++){
            if(courseInformation[i].length() > 25){
                for(int j =0; j < 25; j++){
                    courseEnrollmentFile.writeChar(courseInformation[i].charAt(j));
                }
            }
            else{
                courseEnrollmentFile.writeChars(courseInformation[i]);
                for(int k =0; k < (25- courseInformation[i].length()); k++){
                    courseEnrollmentFile.writeChars(" ");
                }

            }
        }
        courseEnrollmentFile.writeInt(ce.getYear());
        courseEnrollmentFile.writeInt(ce.getCourseID());
        courseEnrollmentFile.writeInt(ce.getStudentID());


    }


    public Enrollment readCourseEnrollmentFile() throws IOException{
        String semester1 = readCourseEnrollmentFileLoop();
        String semester = semester1.trim();
        String grade1 = readCourseEnrollmentFileLoop();
        String grade = grade1.trim();
        String courseNumber = readCourseEnrollmentFileLoop();
        String courseNumber1 = courseNumber.trim();
        String courseName = readCourseEnrollmentFileLoop();
        String courseName1 = courseName.trim();
        String studentName = readCourseEnrollmentFileLoop();
        String studentname1 = studentName.trim();
        int year = courseEnrollmentFile.readInt();
        int cID = courseEnrollmentFile.readInt();
        int sID = courseEnrollmentFile.readInt();
        Enrollment ce =new Enrollment(cID, sID,year,semester,grade, courseNumber1, courseName1, studentname1);
        return ce;
    }

    public String readCourseEnrollmentFileLoop() throws IOException{

        char[] CharArray = new char[25];
        for (int i = 0; i < 25; i++){
            CharArray[i] = courseEnrollmentFile.readChar();
        }
        String info = new String(CharArray);
        return info;

    }

    private long getByteNum(long position){
        return RECORD_SIZE * position;
    }


    public void moveFilePointer(long position) throws IOException{
        courseEnrollmentFile .seek(getByteNum(position));
    }

    public long getNumberOfRecords() throws  IOException{
        return courseEnrollmentFile.length()/RECORD_SIZE;

    }

    public void closeFile() throws  IOException{
        courseEnrollmentFile.close();
    }





}
