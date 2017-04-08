package com.kferenc.jpf.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

public class DateType extends AbstractTimestampType {

    private static final int[] SQL_TYPES = {Types.DATE};

    @Override
    public int[] sqlTypes() {
        return SQL_TYPES;
    }

    @Override
    public Class returnedClass() {
        return Date.class;
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SessionImplementor si, Object o) throws HibernateException, SQLException {
        Date result = null;
        String strResult = resultSet.getString(strings[0]);

        if (strResult != null && !strResult.equals("0000-00-00")) {
            result = resultSet.getDate(strings[0]);
        }

        return result;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int index, SessionImplementor si) throws HibernateException, SQLException {
        if (o == null) {
            preparedStatement.setString(index, "0000-00-00");
        } else {
            preparedStatement.setDate(index, (Date) o);
        }
    }

}
