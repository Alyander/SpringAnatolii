package com.anatolii.springproject1;
import com.anatolii.springproject1.domain.Task;
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
            return session.createQuery("from Task", Task.class).setFirstResult(page == 1 ? 0 : (page*size)).setMaxResults(size).list();
        }
    }
    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from Task where id = :id").setParameter("id", id).executeUpdate();
            session.getTransaction().commit();
        }
    }
    public void update(Task task) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Task taskFromDB = session.get(Task.class, task.getId());
            taskFromDB.setDescription(task.getDescription());
            taskFromDB.setStatus(task.getStatus());
            session.persist(taskFromDB);
            session.getTransaction().commit();
        }
    }
    public void create(Task task) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.getTransaction().commit();
        }
    }
}
