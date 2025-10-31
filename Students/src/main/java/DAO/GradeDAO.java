package DAO;

import Models.Grade;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GradeDAO {
    private static final String FILE_PATH = "Students/src/main/ressources/data/notes.txt";

    List<Grade> grades = new ArrayList<>();
    private static int nextId =1;

    public Grade getById(int id) {
        Grade grade = new Grade();
        for(Grade n: grades) {
            if(n.getId() == id) {
                grade = n;
            }
        }
        return grade;
    }

    public List<Grade> getAll() {
        return grades;
    }

    public List<Grade> getFromStudent(int id) {
        List<Grade> response = new ArrayList<>();
        for(Grade grade : grades) {
            if(grade.getStudentID() == id) {
                response.add(grade);
            }
        }
        return response;
    }

    public void add(Grade grade) {
        grades.add(grade);
    }

    public void remove(int id) {
//        for (Grade grade : grades) {
//            if (grade.getId() == id) {
//                grades.remove(grade);
//            }
//        }
        grades.removeIf(g -> g.getId() == id);
    }
    //save data to .txtfile
    public void saveToFile() throws IOException {
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < grades.size(); i++) {
            lines.add(grades.get(i).getId() + ";" + grades.get(i).getStudentID() + ";" + grades.get(i).getValue());
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
                grades.add(new Grade(id,studentId, value));
                //debug
                //System.out.println(notes.get(0));
            }
        }
        nextId = setNextId();
        //debug
        //System.out.println(nextId);
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
        for(Grade n : grades) {
            ids.add(n.getId());
        }

        return ids.stream()
                .mapToInt(v -> v)
                .max().
                orElse(0);
    }

}
