package sample;

public class Student{
    private int studentID;
    private String name, address, city, state;
    Student(){
        studentID = 0;
        name = "";
        address = "";
        city = "";
        state = "";
    }
    Student(int studentID, String name, String address, String city, String state) {
        this.studentID = studentID;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;

    }


    public int getStudentID(){
        return studentID;
    }
    public void setName(String name) {
        this.name = name;

    }
    public void setAddress(String address) {

        this.address =address;

    }

    public void setCity(String city) {

        this.city = city;
    }
    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }
    public String getName(){
        return  name;
    }

    public String getAddress() {
        return address;
    }

    public String getState(){
        return state;
    }

    public String toString(){
        return "\nStudent ID: " + this.getStudentID() + "\nStudent Name: " + this.getName() +
                "\nStudent City: " + this.getCity() + "\nStudent State: " + this.getState() + "\nStudent Address:"
                + this.getAddress()+"\n";
    }
}
