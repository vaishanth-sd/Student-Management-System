package studentmanagement.service;

import studentmanagement.model.Student;

import java.io.*;
import java.util.ArrayList;

public class StudentService {

    private final String FILE_NAME = "students.txt";

    public void addStudent(Student student) {

        try {

            FileWriter writer = new FileWriter(FILE_NAME, true);

            writer.write(
                    student.getId() + "," +
                    student.getName() + "," +
                    student.getAge() + "\n"
            );

            writer.close();

            System.out.println("Student added successfully");

        } catch (IOException e) {
            System.out.println("Error occurred");
        }
    }

    public ArrayList<Student> viewStudents() {

        ArrayList<Student> students = new ArrayList<>();

        try {

            BufferedReader reader =
                    new BufferedReader(new FileReader(FILE_NAME));

            String line;

            while((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                int age = Integer.parseInt(data[2]);

                Student student = new Student(id, name, age);

                students.add(student);
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Error occurred");
        }

        return students;
    }

    public Student searchStudent(int searchId) {

        ArrayList<Student> students = viewStudents();

            for(Student student : students) {

                if(student.getId() == searchId) {
                    return student;
                }
            }

            return null;
    }

    public void updateStudent(int id, String newName, int newAge) {

            ArrayList<Student> students = viewStudents();

            boolean found = false;

            for(Student student : students) {

                if(student.getId() == id) {

                    student.setName(newName);
                    student.setAge(newAge);

                    found = true;
                    break;
                }
            }

            if(found) {

                try {

                    FileWriter writer =
                            new FileWriter(FILE_NAME);

                    for(Student student : students) {

                        writer.write(
                                student.getId() + "," +
                                student.getName() + "," +
                                student.getAge() + "\n"
                        );
                    }

                    writer.close();

                    System.out.println("Student updated successfully");

                } catch(IOException e) {

                    System.out.println("Error occurred");
                }

            } else {

                System.out.println("Student not found");
            }
        }

        public void deleteStudent(int id) {

                ArrayList<Student> students = viewStudents();

                boolean found = false;

                for(int i = 0; i < students.size(); i++) {

                    if(students.get(i).getId() == id) {

                        students.remove(i);

                        found = true;

                        break;
                    }
                }

                if(found) {

                    try {

                        FileWriter writer =
                                new FileWriter(FILE_NAME);

                        for(Student student : students) {

                            writer.write(
                                    student.getId() + "," +
                                    student.getName() + "," +
                                    student.getAge() + "\n"
                            );
                        }

                        writer.close();

                        System.out.println("Student deleted successfully");

                    } catch(IOException e) {

                        System.out.println("Error occurred");
                    }

                } else {

                    System.out.println("Student not found");
                }
        }


}