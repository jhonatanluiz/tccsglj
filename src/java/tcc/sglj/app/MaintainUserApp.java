/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.sglj.app;

import tcc.sglj.model.User;
import tcc.sglj.dao.DaoUser;

import org.hibernate.Transaction;
import org.hibernate.Session;

import tcc.sglj.util.HibernateUtil;

/**
 *
 * @author jhonatan L S Santos
 */
public class MaintainUserApp extends App<User> {

    private DaoUser dao = new DaoUser();

    public boolean sign(User u) {
        try {

            User a = (User) dao.validCPF(u.getCpf());
            if (a != null && a.getId() != u.getId()) {
                throw new Exception("CPF informado já está em uso no sistema");
            } else {
                a = (User) dao.validEmail(u.getEmail());
                if (a != null && a.getId() != u.getId()) {
                    throw new Exception("Email informado já está em uso no sistema");
                } else {
                    dao.saveOrUpdate(u);
                    return true;
                }
            }
        } catch (Exception e) {
            setErrorMessage("Error: " + e.getMessage());
        }
        return false;
    }

    public boolean delete(int id) {
        try {

            Session session = HibernateUtil.getSession();
            User u = (User) session.load(User.class, id);
            u.setStatus(false);

            Transaction tx = session.beginTransaction();
            session.flush();
            session.saveOrUpdate(u);
            tx.commit();

            return true;
        } catch (Exception e) {
            setErrorMessage(e.getMessage());
        }
        return false;
    }
}