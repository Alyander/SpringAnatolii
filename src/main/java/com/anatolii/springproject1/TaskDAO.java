package com.anatolii.springproject1;
import com.anatolii.springproject1.domain.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            return session.createQuery("from Task", Task.class).setFirstResult(page == 1 ? 0 : ((page-1)*size)).setMaxResults(size).list();
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
            Task taskL = new Task();
            taskL.setDescription(task.getDescription());
            taskL.setStatus(task.getStatus());
            session.persist(taskL);
            session.getTransaction().commit();
        }
    }
    public List<Integer> getPages(int size) {
        int pages;
        List<Integer> pagesList = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
           int all =  session.createNativeQuery("select count(*) from task", int.class).uniqueResult();
           pages =(int)Math.ceil(all/(double)size);
        }
        for (int i = 0; i < pages; i++) {
            pagesList.add(i+1);
        }
        return pagesList;
    }
}
