/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.sglj.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.ProjectionList;
import javax.validation.ValidationException;
import org.hibernate.TransactionException;

import java.sql.BatchUpdateException;
import java.sql.SQLException;


import tcc.sglj.model.User;
import tcc.sglj.util.Paginator;
import tcc.sglj.util.SystemMessage;

/**
 *
 * @author jhonatan L S Santos
 */
public class DaoUser extends DaoHibernate {

    public DaoUser() {
        super();
    }

    public User validCPF(String cpf) {
        User user = (User) getSession().createCriteria(User.class).add(Restrictions.eq("cpf", cpf)).uniqueResult();
        return user;
    }

    public User validEmail(String email) {
        User user = (User) getSession().createCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult();
        return user;
    }

    public User getUser(int id) {
        return (User) getSession().createCriteria(User.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    public void save(User user) {
        try {

            boolean transaction = false;

            if (!getSession().getTransaction().isActive()) {
                getSession().getTransaction().begin();
                transaction = true;
            }

            getValidator().isValid(user);
            getSession().saveOrUpdate(user);

            if (transaction) {
                getSession().getTransaction().commit();
            }

        } catch (HibernateException e) {
            String message = "";

            if (e.getCause() instanceof BatchUpdateException) {
                BatchUpdateException batchUpdateException = (BatchUpdateException) e.getCause();
                SQLException sqlException = batchUpdateException.getNextException();
                message = sqlException.getMessage();
            } else {
                message = e.getCause().toString();
            }

            if (message.indexOf("tb_usuario_nu_cpf_key") != -1) {
                throw new HibernateException(SystemMessage.get("MSG014"));
            } else if (message.indexOf("tb_usuario_ds_email_key") != -1) {
                throw new HibernateException(SystemMessage.get("MSG015"));
            } else {
                throw new HibernateException(e.getCause());
            }
        } catch (ValidationException v) {
            throw new ValidationException(v.getMessage());
        }
    }

    public void list(Paginator paginator) {

        paginator.setRecords(Integer.parseInt(getSession().createCriteria(User.class)
                .add(Restrictions.eq("status", true))
                .setProjection(Projections.rowCount())
                .list().iterator().next().toString()));

        paginator.setTotal((int) Math.ceil((double) paginator.getRecords() / (double) paginator.getRows()));

        paginator.setStart(paginator.getRows() * paginator.getPage() - paginator.getRows());

        Criteria crit = getSession().createCriteria(User.class);
        ProjectionList projection = Projections.projectionList()
                .add(Projections.property("id"))
                .add(Projections.property("name"))
                .add(Projections.property("email"))
                .add(Projections.property("cpf"))
                .add(Projections.property("phone"));
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
