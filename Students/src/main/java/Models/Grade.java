package Models;

public class Grade {
    int id;
    private int studentID;
    private double value;

    public Grade() {
    }

    public Grade(int id , int studentID, double value) {
        this.id = id;
        this.studentID = studentID;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return ""+ this.value;
    }
}
