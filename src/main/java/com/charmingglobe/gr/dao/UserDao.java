package com.charmingglobe.gr.dao;

import com.charmingglobe.gr.entity.Cavalier;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by PANZHENG on 2017/11/17.
 */
@Component
public class UserDao {

    @Autowired
    @Qualifier("sessionFactoryForWriting")
    private SessionFactory sessionFactoryForWriting;

    @Autowired
    @Qualifier("sessionFactoryForReading")
    private SessionFactory sessionFactoryForReading;

    public List<Cavalier> selectUser() {
        Session session = sessionFactoryForReading.getCurrentSession();
        Query query = session.createQuery("from Cavalier where 1=1 order by id asc");
        List<Cavalier> list = query.list();
        return list;
    }

    public List<Cavalier> getUser(String username) {
        Session session = sessionFactoryForReading.getCurrentSession();
        Query query = session.createQuery("from Cavalier where upper(userName)=upper('" + username + "') order by id asc");
        List<Cavalier> list = query.list();
        return list;
    }
    public Cavalier getUser(int id) {
        Session session = sessionFactoryForReading.getCurrentSession();
        Cavalier entity = session.get(Cavalier.class, id);
        return entity;
    }

    public void saveUser(Cavalier user) {
        Session session = sessionFactoryForWriting.getCurrentSession();
        session.saveOrUpdate(user);
    }
}
