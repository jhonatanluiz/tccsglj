/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.sglj.dao;

import java.util.List;
/**
 *
 * @author Jhonatan L S Santos
 */
public interface Dao {

    public void saveOrUpdate(Object object);
    public Object fetch(Object object, String key, Object value);
    public List<Object> fetchAll(Object object, String key, Object value);
}
