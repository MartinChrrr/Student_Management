package Models;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private int id;
    private String name;
    private String email;
    private List<Note> notes = new ArrayList<>();

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

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public void removeNote(Note note) {
        notes.remove(note);
    }

    public double getAverage() {
        if (notes.isEmpty()) return 0.0;
        double sum = 0;
        for (Note note : notes)
        {
            sum += note.getValue();
        }
        return sum / notes.size();
    }
}
