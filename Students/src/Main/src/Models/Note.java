package Models;

public class Note {
    private int nextId = 1;
    private int id;
    private String subject;
    private double value;

    public Note() {
    }

    public Note(String subject, double value) {
        this.id = nextId;
        nextId += 1;
        this.subject = subject;
        this.value = value;
    }

    public int getId() {
        return id;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.subject + ": " + this.value;
    }
}
