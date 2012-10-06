/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.sglj.dao;

import java.util.List;
import java.io.Serializable;
import java.lang.reflect.Type;

import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import tcc.sglj.util.HibernateUtil;
import tcc.sglj.dao.ValidatorEntity;

/**
 *
 * @author Jhonatan
 */
public class DaoHibernate {

    private Session session;
    private ValidatorEntity validator;

    public DaoHibernate() {
        session = HibernateUtil.getSession();
        validator = new ValidatorEntity();
    }

    @SuppressWarnings("unchecked")
    public void setSession(Session s) {
        this.session = s;
    }

    public Session getSession() {
        if (session == null) {
            throw new IllegalStateException("Session não foi inicializado!");
        }
        return session;
    }

    public void saveOrUpdate(Object object) {
        getSession().saveOrUpdate(object);
    }

    public Object fetch(Class type, String field, Object value) {
        List obj = getSession().createCriteria(type).add(Restrictions.eq(field, value)).list();
        if (obj.isEmpty()) {
            return null;
        }
        return obj.get(0);
    }

    public List<Object> fetchAll(Class type, String field, Object value) {
        List obj = getSession().createCriteria(type).add(Restrictions.eq(field, value)).list();
        if (obj.isEmpty()) {
            return null;
        }
        return obj;
    }

    public List<Object> fetchAll(Class type) {
        return getSession().createCriteria(type).list();
    }

    /**
     * @return the validator
     */
    public ValidatorEntity getValidator() {
        return validator;
    }

    /**
     * @param validator the validator to set
     */
    public void setValidator(ValidatorEntity validator) {
        this.validator = validator;
    }
}
