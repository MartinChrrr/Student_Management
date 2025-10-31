package Controller;

import DAO.GradeDAO;
import Models.Grade;

import java.util.List;

public class GradeController {
    private GradeDAO gradeDAO;

    public GradeController(GradeDAO gradeDAO) {
        this.gradeDAO = gradeDAO;
    }
    public void addGrade(Grade grade) { gradeDAO.add(grade); }
    public void removeGrade(int id) { gradeDAO.remove(id); }

    public List<Grade> getAllNotes() { return gradeDAO.getAll(); }
    public Grade getById(int id) { return gradeDAO.getById(id); }

    public List<Grade> getGradesFromStudent(int id) {
        return gradeDAO.getFromStudent(id);
    }

    public void removeGradeFromStudent(int id) {
        for(Grade grade : getGradesFromStudent(id)) {
            removeGrade(grade.getId());
        }
    }

    public int getNewId()
    {
        gradeDAO.generateId();
        return gradeDAO.getNextId();
    }

}
