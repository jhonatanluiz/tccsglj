/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
* jhonatan.luiz.santos@gmail.com
 */
package tcc.sglj.app;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tcc.sglj.util.HibernateUtil;
import tcc.sglj.model.CarMark;
import tcc.sglj.dao.DaoCarMark;
import tcc.sglj.model.CarMark;

/**
 *
 * @author jhonatan L S Santos
 */
public class MaintainCarMarkApp extends App<CarMark> {

    private DaoCarMark dao = new DaoCarMark();

    public boolean sign(CarMark u) {
        try {

            if(dao.validCarMark(u.getNameMark(), u)) {
                dao.saveOrUpdate(u);
            }
            
            
            return true;

        } catch (Exception e) {
            setErrorMessage(e.getMessage());
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            
            Session session = HibernateUtil.getSession();
            
            CarMark CarMarks = (CarMark) session.load(CarMark.class, id);
            CarMarks.setStatus(false);
            
            Transaction tx = session.beginTransaction();
            session.flush();
            session.save(CarMarks);
            tx.commit();

            return true;
        } catch (Exception e) {
            setErrorMessage(e.getMessage());
        }
        return false;
    }
}
