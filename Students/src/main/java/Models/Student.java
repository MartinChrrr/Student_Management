package Models;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private int id;
    private String name;
    private String email;
    private List<Grade> grades = new ArrayList<>();

    public Student() {
    }

    public Student(int id ,String name, String email) {
        this.id = id;

        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Grade> getNotes() {
        return grades;
    }

    public void setNotes(List<Grade> grades) {
        this.grades = grades;
    }

    public void addNote(Grade grade) {
        grades.add(grade);
    }

    public void removeNote(Grade grade) {
        grades.remove(grade);
    }

    public double getAverage() {
        if (grades.isEmpty()) return 0.0;
        double sum = 0;
        for (Grade grade : grades)
        {
            sum += grade.getValue();
        }
        return sum / grades.size();
    }
}
