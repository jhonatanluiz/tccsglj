/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.sglj.action;

import java.util.Iterator;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.InterceptorRef;

import tcc.sglj.model.Service;
import tcc.sglj.util.Paginator;
import tcc.sglj.dao.DaoService;
import tcc.sglj.util.SystemMessage;

@Namespace(value = "/service")
@ParentPackage(value = "default")
public class ServiceAction extends Controller {

    private DaoService dao;
    private Paginator paginator;
    private Service service;

    public ServiceAction() {
        dao = new DaoService();
    }

    @Action(value = "panel", results = {
        @Result(name = "success", location = "/view/service/panel.jsp"),
        @Result(name = "error", location = "/view/service/panel.jsp")
    }, interceptorRefs = {
        @InterceptorRef("loginStack")
    })
    public String panel() {


        try {
            if (getService() != null) {
                Service u = (Service) getDao().fetch(Service.class, "id", getService().getId());
                if (u != null) {
                    setService(u);
                }
            }

        } catch (Exception e) {
            setHelpMessage(false, "alert", e.getMessage());
            return "error";
        }

        return "success";
    }

    @Action(value = "list", results = {
        @Result(name = "success", type = "json", params = {"excludeProperties",
            "message,dao, service, success"
        })
    }, interceptorRefs = {
        @InterceptorRef("loginStack")
    })
    public String list() {

        getDao().list(getPaginator());

        return "success";
    }

    @Action(value = "view", results = {
        @Result(name = "success", location = "/view/service/view.jsp"),
        @Result(name = "error", location = "/view/service/view.jsp")
    }, interceptorRefs = {
        @InterceptorRef("loginStack")
    })
    public String view() {
        Service u = (Service) getDao().fetch(Service.class, "id", getService().getId());
        if (u != null) {
            setService(u);
        } else {
            setService(null);
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
            if (getService() != null) {

                getDao().getSession().getTransaction().begin();
                getDao().getSession().save(getService());
                getDao().getSession().getTransaction().commit();

                setHelpMessage(true, "alert", SystemMessage.get("MSG001"));
            }
        } catch (Exception e) {
            ServletActionContext.getContext().getSession().put("service_error", getService());
            setHelpMessage(false, "alert", e.getMessage());//SystemMessage.get("MSG027"));             
            return "error";
        }
        return "success";
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

    /**
     * @return the dao
     */
    public DaoService getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(DaoService dao) {
        this.dao = dao;
    }

    /**
     * @return the service
     */
    public Service getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(Service service) {
        this.service = service;
    }
}
