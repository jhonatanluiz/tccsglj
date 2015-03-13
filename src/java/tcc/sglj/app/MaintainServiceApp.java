/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.sglj.app;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tcc.sglj.util.HibernateUtil;
import tcc.sglj.model.Service;
import tcc.sglj.dao.DaoService;

/**
 *
 * @author jhonatan L S Santos
 */
public class MaintainServiceApp extends App<Service> {

    private DaoService dao = new DaoService();

    public boolean sign(Service u) {
        try {

            dao.saveOrUpdate(u);
            return true;

        } catch (Exception e) {
            setErrorMessage(e.getMessage());
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            
            Session session = HibernateUtil.getSession();
            
            Service services = (Service) session.load(Service.class, id);
            services.setStatus(false);
            
            Transaction tx = session.beginTransaction();
            session.flush();
            session.save(services);
            tx.commit();

            return true;
        } catch (Exception e) {
            setErrorMessage(e.getMessage());
        }
        return false;
    }
}