/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.sglj.dao;

import java.util.List;
import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import tcc.sglj.model.CarMark;
import tcc.sglj.util.HibernateUtil;
        
/**
 *
 * @author jhonatan
 */
public class DaoCarMark extends DaoHibernate {
    
    public DaoCarMark() {
        super();
    }

    public boolean validCarMark(String mark, CarMark car) {
        List list = getSession().createCriteria(CarMark.class)
                .add(Restrictions
                .eq("nameMark", mark)).list();
        
        if (list.isEmpty()) {
            return true;
        } else if(car != null) {
            CarMark a = (CarMark)list.get(0);
            if(a.getId() == car.getId()) {
                return true;
            }
        }
        return false;
    }

    public CarMark getCarMarkById(int id) {
        Session session = HibernateUtil.getSession();
        CarMark carmark = (CarMark) session.load(CarMark.class, id);
        
        if (carmark == null) {
            return null;
        }
        return carmark;
    }
}
