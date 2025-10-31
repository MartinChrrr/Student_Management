package Controller;

import DAO.NotesDAO;
import Models.Note;
import Models.Student;

import java.util.List;

public class NoteController {
    private NotesDAO notesDAO;

    public NoteController(NotesDAO notesDAO) {
        this.notesDAO = notesDAO;
    }
    public void addNote(Note note) { notesDAO.add(note); }
    public void removeStudent(int id) { notesDAO.remove(id); }

    public List<Note> getAllNotes() { return notesDAO.getAll(); }
    public Note getById(int id) { return notesDAO.getById(id); }



    public int getNewId()
    {
        notesDAO.generateId();
        return notesDAO.getNextId();
    }

}
