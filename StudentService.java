package com.example.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.dao.StudentDAO;
import com.example.entity.Student;

@Service
public class StudentService {

    @Autowired
    private StudentDAO studentDAO;

    @Transactional
    public void addStudent(Student s) {
        studentDAO.save(s);
    }

    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentDAO.getAll();
    }

    @Transactional
    public void updateStudent(Student s) {
        studentDAO.update(s);
    }

    @Transactional
    public void deleteStudent(int id) {
        studentDAO.delete(id);
    }
}
