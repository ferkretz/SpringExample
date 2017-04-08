package com.kferenc.jpf.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<E, PK extends Serializable> {

    private final Class<E> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDao() {
        this.persistentClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public E getByKey(PK key) {
        return (E) getSession().get(persistentClass, key);
    }

    @SuppressWarnings("unchecked")
    public E getBy(String field, Object value) {
        List<E> list = createCriteria().add(Restrictions.eq(field, value)).list();

        if (list.isEmpty()) {
            return null;
        }

        E entity = list.get(0);

        return entity;
    }

    public void persist(E entity) {
        getSession().persist(entity);
    }

    public void delete(E entity) {
        getSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public PK save(E entity) {
        return (PK) getSession().save(entity);
    }

    public void update(E entity) {
        getSession().update(entity);
    }

    protected Criteria createCriteria() {
        return getSession().createCriteria(persistentClass);
    }

    protected Criteria createCriteria(Class persistentClass) {
        return getSession().createCriteria(persistentClass);
    }

    public Long fieldValueCount(String field, Object value) {
        return (Long) createCriteria()
                .setProjection(Projections.rowCount()).add(Restrictions.eq(field, value)).uniqueResult();
    }

}
