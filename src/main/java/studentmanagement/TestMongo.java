package studentmanagement;

import studentmanagement.model.Student;
import studentmanagement.service.StudentDAO;

public class TestMongo {

    public static void main(String[] args) {

        StudentDAO dao = new StudentDAO();

        Student s1 = new Student(2, "Arun", 20);

        dao.addStudent(s1);

        dao.viewStudents();

    }
}