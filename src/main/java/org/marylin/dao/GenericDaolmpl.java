package org.marylin.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.marylin.util.Hibernateutil.getSessionFactory;

public class GenericDaolmpl <T> implements IGenericDAO<T>  {

    private org.hibernate.SessionFactory SessionFactory;

    public GenericDaolmpl(Class<T> cl,SessionFactory sessionFactory){
        this.SessionFactory = sessionFactory;
        if (sessionFactory == null)
            throw new RuntimeException("La fabrica de secion es null");
    }

    @Override
    public T get(Class<T> cl, Integer id) {
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        T element = session.get(cl,id);
        session.getTransaction().commit();
        return element;
    }

    @Override
    public T get(Class cl, Long id) {
        return null;
    }

    @Override
    public T save(T object) {
        Session Session = SessionFactory.openSession();
        Session.beginTransaction();
        Session.save(object);
        Session.getTransaction().commit();
        return object;
    }

    @Override
    public void update(Object objec) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.update(objec);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Object object) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(object);
        session.getTransaction().commit();

    }

    @Override
    public List<T> query(String hsql, Map<String, Objects> params) {
        Session session = SessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery(hsql);
        if(params != null) {
            for (String i : params.keySet()) {
                query.setParameter(i, params.get(i));
            }
        }

        List<T> result = null;
        if((!hsql.toUpperCase().contains("DELETE"))
                && (!hsql.toUpperCase().contains("UPDATE"))
                && (!hsql.toUpperCase().contains("INSERT"))
        ) {
            result = query.list();
        }
        session.getTransaction().commit();
        return result;
    }
}