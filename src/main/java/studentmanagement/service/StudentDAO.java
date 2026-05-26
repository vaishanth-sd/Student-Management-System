package studentmanagement.service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

import com.mongodb.client.model.IndexOptions;
import com.mongodb.MongoWriteException;

import studentmanagement.database.MongoDBConnection;
import studentmanagement.model.Student;

public class StudentDAO {

    public StudentDAO() {

        collection.createIndex(
                new Document("id", 1),
                new IndexOptions().unique(true)
        );
    }

    MongoDatabase database = MongoDBConnection.connect();

    MongoCollection<Document> collection =
            database.getCollection("students");

    public void addStudent(Student student) {

            try {

            Document doc = new Document("id", student.getId())
                    .append("name", student.getName())
                    .append("age", student.getAge());

            collection.insertOne(doc);

            System.out.println("Student Added to MongoDB");

            } catch (MongoWriteException e) {

                System.out.println("Student ID already exists");
            }
    }

    public void viewStudents() {

        for (Document doc : collection.find()) {

            System.out.println(
                    "ID: " + doc.getInteger("id") +
                    ", Name: " + doc.getString("name") +
                    ", Age: " + doc.getInteger("age")
            );
        }
    }

    public void searchStudent(int id) {

        Document doc = collection.find(eq("id", id)).first();

        if (doc != null) {

            System.out.println(
                    "Student Found:\n" +
                    "ID: " + doc.getInteger("id") +
                    ", Name: " + doc.getString("name") +
                    ", Age: " + doc.getInteger("age")
            );

        } else {

            System.out.println("Student Not Found");
        }
    }

    public void updateStudent(int id, String newName, int newAge) {

        collection.updateOne(
                eq("id", id),
                new Document("$set",
                        new Document("name", newName)
                                .append("age", newAge))
        );

        System.out.println("Student Updated");
    }

    public void deleteStudent(int id) {

        collection.deleteOne(eq("id", id));

        System.out.println("Student Deleted");
    }
}