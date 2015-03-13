/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.sglj.dao;

import java.util.List;
import java.io.Serializable;
import org.hibernate.Criteria;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import tcc.sglj.model.Service;
import tcc.sglj.util.Paginator;
import tcc.sglj.util.HibernateUtil;

/**
 *
 * @author jhonatan L S Santos
 */
public class DaoService extends DaoHibernate{

    public DaoService() {
        super();
    }

    public boolean validService(String cpf, Service u) {
        /*List user = getSession().createCriteria(User.class).add(Restrictions.eq("cpf", cpf)).list();
        if (user.isEmpty()) {
            return true;
        } else if(u != null) {
            User a = (User)user.get(0);
            if(a.getId() == u.getId()) {
                return true;
            }
        }*/
        return false;
    }

    public Service getServiceById(int id) {
        Session session = HibernateUtil.getSession();
        Service service = (Service) session.load(Service.class, id);
        
        if (service == null) {
            return null;
        }
        return service;
    }
    
    public void list(Paginator paginator) {

        paginator.setRecords(Integer.parseInt(getSession().createCriteria(Service.class)
                .add(Restrictions.eq("status", true))
                .setProjection(Projections.rowCount())
                .list().iterator().next().toString()));

        paginator.setTotal((int) Math.ceil((double) paginator.getRecords() / (double) paginator.getRows()));

        paginator.setStart(paginator.getRows() * paginator.getPage() - paginator.getRows());

        Criteria crit = getSession().createCriteria(Service.class);
        ProjectionList projection = Projections.projectionList()
                .add(Projections.property("id"))
                .add(Projections.property("name"))
                .add(Projections.property("description"))
                .add(Projections.property("price"));                
        crit.add(Restrictions.eq("status", true));
        crit.setProjection(projection);

        if (paginator.getSord().equals("desc")) {
            crit.addOrder(Order.desc(paginator.getSidx()));
        } else {
            crit.addOrder(Order.asc(paginator.getSidx()));
        }

        if (!paginator.getField().equals("") && !paginator.getSearch().equals("")) {

            if (paginator.getField().equals("id")) {
                crit.add(Restrictions.eq(paginator.getField(), Integer.parseInt(paginator.getSearch())));
            } else if (paginator.getField().equals("phone") || paginator.getField().equals("cpf")) {
                crit.add(Restrictions.eq(paginator.getField(), paginator.getSearch()));
            } else {
                crit.add(Restrictions.ilike(paginator.getField(), "%" + paginator.getSearch() + "%"));
            }
        }

        if (!paginator.getField().equals("") && !paginator.getSearch().equals("")) {
            paginator.setRecords(crit.list().size());
            paginator.setTotal((int) Math.ceil((double) paginator.getRecords() / (double) paginator.getRows()));
        }

        crit.setFirstResult(paginator.getStart());
        crit.setMaxResults(paginator.getRows());
        paginator.setData(crit.list());
    }
}
