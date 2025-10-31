package DAO;

import Models.Student;

import java.io.*;
import java.util.*;

public class StudentDAO {
    private static final String FILE_PATH = "Students/src/main/ressources/data/students.txt";
    private int nextId =1;
    private Map<Integer, Student> students = new HashMap<>();

    public List<Student> getAll() {
        System.out.println(students.get(1).getEmail());

        return new ArrayList<>(students.values());
    }

    public Student getById(int id) {
        return students.get(id);
    }

    public void add(Student student) {
        students.put(student.getId(), student);
    }

    public void remove(int id) {
        students.remove(id);
    }
    //data to .txtfile

    public void saveToFile() throws IOException {
        List<String> lines = new ArrayList<>();
        for (Student s : students.values()) {
            lines.add(s.getId() + ";" + s.getName() + ";" + s.getEmail());
        }
        DataStorage.writeToFile(FILE_PATH, lines);
    }

    //data from .txtfile

    public void loadFromFile() throws IOException {
        for (String line : DataStorage.readFromFile(FILE_PATH)) {
            String[] parts = line.split(";");
            if (parts.length >= 3){
                int id = Integer.parseInt(parts[0]);

                students.put(id, new Student(id, parts[1], parts[2]));
                //System.out.println(students.get(1).getName());

            }
        }
    }

    // //generation id part
    public int generateId() {
        return nextId++;
    }

    public int getNextId() {
        return nextId;

    }
}
