package Controller;

import DAO.StudentDAO;
import Models.Student;

import java.util.List;

public class StudentController {
    private StudentDAO dao;

    public StudentController(StudentDAO dao) {
        this.dao = dao;
    }


    public void addStudent(Student s) { dao.add(s); }
    public void removeStudent(int id) { dao.remove(id); }
    public List<Student> getAllStudents() { return dao.getAll(); }
    public Student getById(int id) { return dao.getById(id); }
    public int getNewId()
    {
        dao.generateId();
        return dao.getNextId();
    }
}
