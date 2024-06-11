package org.marylin.Services;

import org.marylin.dao.IGenericDAO;

import java.util.List;

public interface IGenericService <T> extends IGenericDAO<T> {

        T get(Class<T> cl, Integer id);

        T get(Class<T> cl, Long id);

        T save(T object);

        void update(T object);

        void delete(T object);

        List<T> getAll();

        void deleteAll();

        T getById(int i);

        T getId(Long i);

        T getByName(String name);
}

