package DAO;

import Models.Note;
import Models.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NotesDAO {
    private static final String FILE_PATH = "Students/src/main/ressources/data/notes.txt";

    List<Note> notes = new ArrayList<>();
    private static int nextId =1;

    public Note getById(int id) {
        Note note = new Note();
        for(Note n: notes) {
            if(n.getId() == id) {
                note = n;
            }
        }
        return note;
    }

    public List<Note> getAll() {
        return notes;
    }

    public void add(Note note) {
        notes.add(note);
    }

    public void remove(int id) {
        notes.remove(id);
    }
    //save data to .txtfile
    public void saveToFile() throws IOException {
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < notes.size(); i++) {
            lines.add(notes.get(i).getId() + ";" + notes.get(i).getStudentID() + ";" + notes.get(i).getValue());
        }

        DataStorage.writeToFile(FILE_PATH, lines);
    }


    //load data from .txtfile
    public void loadFromFile() throws IOException {
        for (String line : DataStorage.readFromFile(FILE_PATH)) {
            String[] parts = line.split(";");
            if (parts.length >= 3){
                int id = Integer.parseInt(parts[0]);
                int studentId = Integer.parseInt(parts[1]);
                double value = Double.parseDouble(parts[2]);
                notes.add(new Note(id,studentId, value));
                //debug
                //System.out.println(notes.get(0));
            }
        }
        nextId = setNextId();
        //debug
        System.out.println(nextId);
    }
    //generation id part
    public int generateId() {
        return nextId++;
    }

    public int getNextId() {
        return nextId;

    }
    private int setNextId() {
        List<Integer> ids = new ArrayList<Integer>();
        for(Note n : notes) {
            ids.add(n.getId());
        }

        return ids.stream()
                .mapToInt(v -> v)
                .max().
                orElse(0);
    }

}
