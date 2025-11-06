package com.example.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.entity.Student;

@Repository
public class StudentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(student);
    }

    public Student getById(int id) {
        return sessionFactory.getCurrentSession().get(Student.class, id);
    }

    public List<Student> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Student", Student.class).list();
    }

    public void update(Student student) {
        sessionFactory.getCurrentSession().merge(student);
    }

    public void delete(int id) {
        Student s = getById(id);
        if (s != null)
            sessionFactory.getCurrentSession().remove(s);
    }
}
