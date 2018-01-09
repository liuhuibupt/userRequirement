package com.charmingglobe.gr.hibernate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;
import org.postgresql.util.PGobject;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;
import java.util.Properties;

/**
 * Created by PANZHENG on 2017/5/9.
 */

public class JSONBUserType implements ParameterizedType, UserType {

    public static final String JSONB_TYPE = "jsonb";

    //1
    public void setParameterValues(Properties properties) {
    }

    //2
    public int[] sqlTypes() {
        return new int[]{Types.JAVA_OBJECT};
    }

    //3
    public Class returnedClass() {
        return Object.class;
    }

    //4
    public boolean equals(Object x, Object y) throws HibernateException {
        if (x == y) {
            return true;
        }

        if ((x == null) || (y == null)) {
            return false;
        }

        return x.equals(y);
    }

    //5
    public int hashCode(Object x) throws HibernateException {
        assert (x != null);
        return x.hashCode();
    }

    public Object nullSafeGet(ResultSet resultSet, String[] names, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException, SQLException {

        final String json = resultSet.getString(names[0]);
        Gson gson =  new GsonBuilder().create();
        return json == null ? null : gson.fromJson(json, new TypeToken<Map<String, String>>() {}.getType());
    }

    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {
        Gson gson =  new GsonBuilder().create();
        final String json = value == null ? null : gson.toJson(value);
        PGobject pgo = new PGobject();
        pgo.setType(JSONB_TYPE);
        pgo.setValue(json);
        st.setObject(index, pgo);
    }

    public Object deepCopy(Object o) throws HibernateException {
        return o;
    }

    public boolean isMutable() {
        return true;
    }

    public Serializable disassemble(Object o) throws HibernateException {
        return (Serializable) o;
    }

    public Object assemble(Serializable serializable, Object o) throws HibernateException {
        return o;
    }

    public Object replace(Object o, Object o1, Object o2) throws HibernateException {
        return o;
    }
}
