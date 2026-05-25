package studentmanagement;

import java.util.*;
import studentmanagement.model.Student;
import studentmanagement.service.StudentService;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        StudentService service =new StudentService();

        while(true){

            System.out.println("Student Management System");
            System.out.println("1.Add Student");
            System.out.println("2.View Student");
            System.out.println("3.Search Student");
            System.out.println("4.Update Student");
            System.out.println("5.Delete Student");
            System.out.println("6.Exit");

            int choice;
            try{
                choice=sc.nextInt();
            }
            catch(Exception e){
                System.out.println("Invalid Option");
                sc.nextLine();
                continue;
            }

            switch(choice){

                case 1:
                    System.out.println("Enter Student Id");
                    int id=sc.nextInt();
                    
                    System.out.println("Enter Student Name");
                    String name=sc.next();

                    System.out.println("Enter Student Age");
                    int age=sc.nextInt();

                    Student s =new Student(id,name,age);

                    service.addStudent(s);

                    System.out.println("Student Added");

                    break;

               case 2:

                    ArrayList<Student> students =
                            service.viewStudents();

                    for(Student st : students) {
                        System.out.println(st);
                    }

                    break;

                case 3:
                    System.out.println("Enter Student id to search:");
                    int searchid=sc.nextInt();

                    Student res=service.searchStudent(searchid);

                    if(res!=null){
                        System.out.println(res);
                    }
                    else{
                        System.out.println("No Student Found");
                    }
                    break;

                case 4:
                        System.out.print("Enter Student ID: ");
                        int updateId = sc.nextInt();

                        sc.nextLine();

                        System.out.print("Enter New Name: ");
                        String newName = sc.nextLine();

                        System.out.print("Enter New Age: ");
                        int newAge = sc.nextInt();

                        service.updateStudent(updateId, newName, newAge);

                        break;

                case 5:
                    System.out.println("Enter Student id to del:");
                    int delid=sc.nextInt();

                    service.deleteStudent(delid);
                    break;
                
                case 6:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                
                default:
                    System.out.println("Invaid Option");
            }
        }
    }    
}
