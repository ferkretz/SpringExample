package com.kferenc.jpf.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

public class TimestampType extends AbstractTimestampType {

    private static final int[] SQL_TYPES = {Types.TIMESTAMP};

    @Override
    public int[] sqlTypes() {
        return SQL_TYPES;
    }

    @Override
    public Class returnedClass() {
        return Timestamp.class;
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SessionImplementor si, Object o) throws HibernateException, SQLException {
        Timestamp result = null;
        String strResult = resultSet.getString(strings[0]);

        if (strResult != null && !strResult.equals("0000-00-00 00:00:00")) {
            result = resultSet.getTimestamp(strings[0]);
        }

        return result;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int index, SessionImplementor si) throws HibernateException, SQLException {
        if (o == null) {
            preparedStatement.setString(index, "0000-00-00 00:00:00");
        } else {
            preparedStatement.setTimestamp(index, (Timestamp) o);
        }
    }

}
