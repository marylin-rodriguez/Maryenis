package org.marylin.Services;

import org.hibernate.SessionFactory;
import org.marylin.dao.GenericDaolmpl;
import org.marylin.dao.IGenericDAO;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GenericServiceImpl<T> implements IGenericService<T> {
    private IGenericDAO<T> dao;
    private Class<T> cl;
    SessionFactory session;

    public GenericServiceImpl(Class<T> cl, SessionFactory sessionFactory){
        this.cl = cl;
        dao = new GenericDaolmpl<>(cl, sessionFactory);
        session = sessionFactory;
    }
    @Override
    public T get(Class<T> cl, Integer id) {
        return (T) dao.get(cl, id);
    }

    @Override
    public T get(Class<T> cl, Long id) {
        return null;
    }

    @Override
    public T save(T object) {
        return (T) dao.save(object);
    }

    @Override
    public void update(T objec) {

    }

    @Override
    public void delete(T object) {

    }

    @Override
    public List<T> query(String hsql, Map<String, Objects> params) {
        return (List<T>) dao.query(hsql, params);
    }

    @Override
    public List<T> getAll() {
        return dao.query("FROM " + cl.getSimpleName(), null) == null ? null : dao.query("FROM " +cl.getSimpleName(), null);
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public T getById(int i) {
        return null;
    }

    @Override
    public T getId(Long i) {
        return null;
    }

    @Override
    public T getByName(String name) {
        return null;
    }
}
