/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
* jhonatan.luiz.santos@gmail.com
 */
package tcc.sglj.app;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Date;

import tcc.sglj.model.User;
import tcc.sglj.model.LogAccess;
import tcc.sglj.util.HibernateUtil;

/**
 *
 * @author jhonatan
 */
public class Authenticate {

    public static User authentic(User user) {
        Session session = HibernateUtil.getSession();
         
         Criteria crit = session.createCriteria(User.class)
                 .add(Restrictions.eq("email", user.getEmail()))
                 .add(Restrictions.eq("password", user.getPassword()))
                 .add(Restrictions.eq("status", true));

        List result = crit.list();
        if (result.isEmpty()) {
            return null;
        }
        
        Transaction transaction = session.beginTransaction();
        User u = (User)result.get(0);
        
        LogAccess log = new LogAccess();
        log.setUser(u);
        log.setDateAccess(new Date());        
        session.save(log);
        transaction.commit();
        
        HibernateUtil.getSession().close();
        return u;
    }
}

