package sample;

import java.io.IOException;
import java.io.RandomAccessFile;

public class CourseItemFile{
    private RandomAccessFile courseFile;
    private long RECORD_SIZE = 204;

    CourseItemFile(String filename) throws IOException {
        courseFile = new RandomAccessFile(filename, "rw");

    }


    public void writeCourseItem(Course c) throws IOException{
        String str = c.getCourseID();
        String str2 = c.getCourseName();
        String str3 = c.getDepartmentName();
        String str4 = c.getInstructorName();
        String[] courseInformation = {str,str2,str3,str4};

        for(int i = 0; i < courseInformation.length; i++){
            if(courseInformation[i].length() > 25){
                for(int j =0; j < 25; j++){
                    courseFile.writeChar(courseInformation[i].charAt(j));
                }
            }
            else{
                courseFile.writeChars(courseInformation[i]);
                for(int k =0; k < (25- courseInformation[i].length()); k++){
                    courseFile.writeChars(" ");
                }

            }
        }
        courseFile.writeInt(c.getCourseNumber());

    }


    public Course readCourseFile() throws IOException{
        String courseID = readCourseFileLoop();
        String courseName = readCourseFileLoop();
        String departmentName = readCourseFileLoop();
        String instructorName = readCourseFileLoop();
        int ID = courseFile.readInt();
        Course c =new Course( ID,instructorName.trim(), courseName.trim(), departmentName.trim(), courseID.trim());
        return c;
    }

    public String readCourseFileLoop() throws IOException{

        char[] CharArray = new char[25];
        for (int i = 0; i < 25; i++){
            CharArray[i] = courseFile.readChar();
        }
        String info = new String(CharArray);
        return info;

    }

    private long getByteNum(long position){
        return RECORD_SIZE * position;
    }


    public void moveFilePointer(long position) throws IOException{
        courseFile .seek(getByteNum(position));
    }

    public long getNumberOfRecords() throws  IOException{
        return courseFile.length()/RECORD_SIZE;

    }

    public void closeFile() throws  IOException{ ;
        courseFile.close();
    }


}