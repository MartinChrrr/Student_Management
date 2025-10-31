package DAO;

import Models.Note;
import Models.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NotesDAO {
    private static final String FILE_PATH = "Students/src/main/ressources/data/notes.txt";

    List<Note> notes = new ArrayList<>();
    private int nextId =1;

    //data to .txtfile
    public void saveToFile() throws IOException {
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < notes.size(); i++) {
            lines.add(notes.get(i).getId() + ";" + notes.get(i).getStudentID() + ";" + notes.get(i).getValue());
        }

        DataStorage.writeToFile(FILE_PATH, lines);
    }


    //data from .txtfile
    public void loadFromFile() throws IOException {
        for (String line : DataStorage.readFromFile(FILE_PATH)) {
            String[] parts = line.split(";");
            if (parts.length >= 2){
                int id = Integer.parseInt(parts[0]);
                int studentId = Integer.parseInt(parts[1]);
                int value = Integer.parseInt(parts[2]);
                notes.add(new Note(id,studentId, value));


            }
        }
    }
    //generation id part
    public int generateId() {
        return nextId++;
    }

    public int getNextId() {
        return nextId;

    }
}
