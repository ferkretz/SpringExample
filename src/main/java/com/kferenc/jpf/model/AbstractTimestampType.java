package com.kferenc.jpf.model;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

public abstract class AbstractTimestampType implements UserType {

    @Override
    abstract public int[] sqlTypes();

    @Override
    abstract public Class returnedClass();

    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        if (o == o1) {
            return true;
        } else if (o == null || o1 == null) {
            return false;
        } else {
            return o.equals(o1);
        }
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        int hash = 0;
        hash += o.hashCode();
        return hash;
    }

    @Override
    abstract public Object nullSafeGet(ResultSet rs, String[] strings, SessionImplementor si, Object o) throws HibernateException, SQLException;

    @Override
    abstract public void nullSafeSet(PreparedStatement ps, Object o, int i, SessionImplementor si) throws HibernateException, SQLException;

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        return o;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object o) throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object assemble(Serializable srlzbl, Object o) throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }

}
