package sample;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class StudentItemFile {
    private final int RECORD_SIZE = 204;
    private RandomAccessFile studentFile;

    public StudentItemFile(String filename) throws FileNotFoundException {
        studentFile = new RandomAccessFile(filename, "rw");

    }

    public void writeStudentItem(Student student1) throws IOException {
        String str = student1.getAddress();
        String str2 = student1.getCity();
        String str3 = student1.getName();
        String str4 = student1.getState();
        String[] informationArray = {str, str2, str3, str4};

        for (int i = 0; i < informationArray.length; i++) {
            if (informationArray[i].length() > 25) {
                for (int j = 0; j < 25; j++) {
                    studentFile.writeChar(informationArray[i].charAt(i));

                }
            } else {
                studentFile.writeChars(informationArray[i]);
                for (int k = 0; k < (25 - informationArray[i].length()); k++) {
                    studentFile.writeChars(" ");
                }
            }
        }
        studentFile.writeInt(student1.getStudentID());
    }


    public Student readStudentItemFile() throws IOException {
        String address = readStudentItemFileLoop();
        String city = readStudentItemFileLoop();
        String name = readStudentItemFileLoop();
        String state = readStudentItemFileLoop();
        int ID = studentFile.readInt();
        Student s = new Student(ID, name, address, city, state);
        return s;
    }


    public String readStudentItemFileLoop() throws IOException {

        char[] CharArray = new char[25];
        for (int i = 0; i < 25; i++) {
            CharArray[i] = studentFile.readChar();
        }
        String info = new String(CharArray);
        return info;

    }


    private long getByteNum(long position) {
        return RECORD_SIZE * position;
    }


    public void moveFilePointer(long position) throws IOException {
        studentFile.seek(getByteNum(position));
    }

    public long getNumberOfRecords() throws IOException {
        return studentFile.length() / RECORD_SIZE;

    }

    public void closeFile() throws IOException {
        studentFile.close();
    }

}

