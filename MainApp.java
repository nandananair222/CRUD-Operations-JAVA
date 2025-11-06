package com.example;

import com.example.config.AppConfig;
import com.example.entity.Student;
import com.example.service.StudentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        StudentService service = context.getBean(StudentService.class);

        // Create
        Student s1 = new Student("Alice", "alice@example.com");
        Student s2 = new Student("Bob", "bob@example.com");
        service.addStudent(s1);
        service.addStudent(s2);

        // Read
        System.out.println("All Students:");
        service.getAllStudents().forEach(System.out::println);

        // Update
        s1.setEmail("alice123@example.com");
        service.updateStudent(s1);

        // Delete
        service.deleteStudent(s2.getId());

        System.out.println("After Update/Delete:");
        service.getAllStudents().forEach(System.out::println);

        context.close();
    }
}
