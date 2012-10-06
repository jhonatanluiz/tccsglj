/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.sglj.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import tcc.sglj.model.City;
import tcc.sglj.model.State;
import tcc.sglj.dao.DaoHibernate;

/**
 *
 * @author jhonatan
 */
@Namespace(value = "uf")
@ParentPackage(value = "json-default")
public class UFAction {
    
    private List citys;
    private State state;

    @Action(value = "/city", results = {
        @Result(name = "success", location = "/view/uf/city.jsp")
    })
    public String city() {
        DaoHibernate dao = new DaoHibernate();
        State s = (State) dao.fetch(State.class, "id", getState().getId());
        setCitys(dao.fetchAll(City.class, "state", s));
        return "success";
    }

    /**
     * @return the citys
     */
    public List getCitys() {
        return citys;
    }

    /**
     * @param citys the citys to set
     */
    public void setCitys(List citys) {
        this.citys = citys;
    }

    /**
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(State state) {
        this.state = state;
    }
}
