package com.anatolii.springproject1;

import com.anatolii.springproject1.domain.Status;
import com.anatolii.springproject1.domain.Task;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskDAO {
    private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public List<Task> getAllByPage(int page, int size) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Task", Task.class).setFirstResult(page == 1 ? 1 : page*size).setMaxResults(size).list();
        }
    }
    public void delete(Task task) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from Task where id = :id").setParameter("id", task.getId()).executeUpdate();
            session.getTransaction().commit();
        }
    }
    public void update(Task task, Status status, String description) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            task.setStatus(status);
            task.setDescription(description);
            session.persist(task);
            session.getTransaction().commit();
        }
    }
    public void create(Task task) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(task);
            session.getTransaction().commit();
        }
    }
}
