/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.sglj.action;

import java.util.List;
import java.util.Date;
import java.util.Iterator;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.InterceptorRef;


import tcc.sglj.model.User;
import tcc.sglj.model.City;
import tcc.sglj.model.State;
import tcc.sglj.model.Perfil;
import tcc.sglj.model.Address;
import tcc.sglj.dao.DaoUser;
import tcc.sglj.util.Encrypt;
import tcc.sglj.util.Paginator;
import tcc.sglj.util.SystemMessage;

/**
 *
 * @author jhonatan
 */
@Namespace(value = "/user")
@ParentPackage(value = "default")
public class UserAction extends Controller {

    private User user;
    private int status;
    private DaoUser dao;
    private Address address;
    private List<Object> city;
    private List<Object> state;
    private List<Object> perfil;
    private Paginator paginator;

    public UserAction() {
        this.dao = new DaoUser();
    }

    @Action(value = "panel", results = {
        @Result(name = "success", location = "/view/user/panel.jsp"),
        @Result(name = "error", location = "/view/user/panel.jsp")
    }, interceptorRefs = {
        @InterceptorRef("loginStack")
    })
    public String panel() {


        try {

            setState(getDao().fetchAll(State.class));
            setPerfil(getDao().fetchAll(Perfil.class));

            if (getCurrentUser() != null && getCurrentUser().getPerfil().getId() == Perfil.PERFIL_USUARIO) {
                setUser(getCurrentUser());
            } else if (getUser() != null && getUser().getId() != 0) {
                setUser(getDao().getUser(getUser().getId()));
                if (getUser().getPerfil().getId() == Perfil.PERFIL_ADMINISTRADOR && (getCurrentUser() == null || getCurrentUser().getPerfil().getId() != Perfil.PERFIL_ADMINISTRADOR)) {
                    setUser(null);
                    throw new Exception(SystemMessage.get("MSG026"));
                }
            }

            User u = (User) ServletActionContext.getContext().getSession().get("user_error");
            if (u != null) {
                setUser(u);
                ServletActionContext.getContext().getSession().put("user_error", null);
            }

            if (getUser() != null) {
                for (Address a : getUser().getAddress()) {
                    City c = (City) getDao().fetch(City.class, "id", a.getCity().getId());
                    setCity(getDao().fetchAll(City.class, "state", c.getState()));
                    a.setCity(c);
                }
            }

            if (getCurrentUser() != null && getCurrentUser().getPerfil().getId() != Perfil.PERFIL_ADMINISTRADOR) {
                Iterator p = getPerfil().iterator();
                while (p.hasNext()) {
                    Perfil current = (Perfil) p.next();
                    if (current.getId() == Perfil.PERFIL_ADMINISTRADOR) {
                        p.remove();
                    }
                }
            }
        } catch (Exception e) {
            setHelpMessage(false, "alert", e.getMessage());
            return "error";
        }

        return "success";
    }

    @Action(value = "sign", results = {
        @Result(name = "success", type = "redirectAction", params = {
            "actionName", "panel", "message", "%{message}"
        }),
        @Result(name = "error", type = "redirectAction", params = {
            "actionName", "panel", "message", "%{message}"
        })
    }, interceptorRefs = {
        @InterceptorRef("loginStack")
    })
    public String sign() {

        try {

            if (getUser() != null) {

                getUser().setPassword(new Encrypt(getUser().getPassword()).encrypt());
                getUser().setSign(new Date());

                if (!getUser().getAddress().isEmpty()) {
                    for (Address a : getUser().getAddress()) {
                        a.setUser(getUser());
                    }
                }

                if (getCurrentUser() == null) {
                    Perfil p = new Perfil();
                    p.setId(1);
                    getUser().setPerfil(p);
                    getUser().setStatus(true);
                } else {
                    if (getCurrentUser().getPerfil().getId() != Perfil.PERFIL_USUARIO) {
                        if (getStatus() == 1) {
                            getUser().setStatus(true);
                        } else {
                            getUser().setStatus(false);
                        }
                    }
                }

                getDao().save(getUser());
                
                setHelpMessage(true, "alert", SystemMessage.get("MSG001"));
            }

        } catch (Exception e) {
            ServletActionContext.getContext().getSession().put("user_error", getUser());
            setHelpMessage(false, "alert", e.getMessage());
            return "error";
        }
        return "success";
    }

    @Action(value = "list", results = {
        @Result(name = "success", type = "json", params = {"excludeProperties",
            "message,user, dao, address, perfil, state, city, status, success"
        })
    }, interceptorRefs = {
        @InterceptorRef("loginStack")
    })
    public String list() {

        getDao().list(getPaginator());

        return "success";
    }

    @Action(value = "view", results = {
        @Result(name = "success", location = "/view/user/view.jsp"),
        @Result(name = "error", location = "/view/user/view.jsp")
    }, interceptorRefs = {
        @InterceptorRef("loginStack")
    })
    public String view() {
        User u = (User) getDao().fetch(User.class, "id", getUser().getId());
        if (u != null) {
            setUser(u);
        } else {
            setUser(null);
        }
        return "success";
    }

    @Action(value = "edit", results = {
        @Result(name = "success", type = "redirectAction", params = {
            "actionName", "panel", "message", "%{message}", "user.id", "%{user.id}"
        }),
        @Result(name = "error", type = "redirectAction", params = {
            "actionName", "panel", "message", "%{message}", "user.id", "%{user.id}"
        })
    }, interceptorRefs = {
        @InterceptorRef("loginStack")
    })
    public String edit() {
        try {
            if (getUser() == null) {
                throw new Exception(SystemMessage.get("MSG018"));
            }

            User u = getDao().getUser(getUser().getId());

            u.setName(getUser().getName());
            u.setEmail(getUser().getEmail());
            u.setCpf(getUser().getCpf());
            u.setAddress(getUser().getAddress());
            u.setPhone(getUser().getPhone());

            if (!u.getAddress().isEmpty()) {
                for (Address a : u.getAddress()) {
                    a.setUser(u);
                }
            }

            if (getStatus() == 1) {
                u.setStatus(true);
            } else {
                u.setStatus(false);
            }

            if (getCurrentUser() != null && getCurrentUser().getPerfil().getId() != Perfil.PERFIL_USUARIO) {
                u.setPerfil(getUser().getPerfil());
            }

            getDao().save(u);

            setHelpMessage(true, "alert", SystemMessage.get("MSG019"));

        } catch (Exception e) {

            setHelpMessage(false, "alert", e.getMessage());
            return "error";
        }
        return "success";
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the dao
     */
    public DaoUser getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(DaoUser dao) {
        this.dao = dao;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @return the perfil
     */
    public List<Object> getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(List<Object> perfil) {
        this.perfil = perfil;
    }

    /**
     * @return the state
     */
    public List<Object> getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(List<Object> state) {
        this.state = state;
    }

    /**
     * @return the city
     */
    public List<Object> getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(List<Object> city) {
        this.city = city;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the paginator
     */
    public Paginator getPaginator() {
        return paginator;
    }

    /**
     * @param paginator the paginator to set
     */
    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }
}
