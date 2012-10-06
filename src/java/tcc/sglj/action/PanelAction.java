/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.sglj.action;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

/**
 *
 * @author jhonatan
 */
@Namespace(value = "/panel")
@ParentPackage(value = "default")
public class PanelAction {

    @Action(value = "panel", results = {
        @Result(name = "success", location = "/view/panel/panel.jsp")
    })
    public String panel() {
        return "success";
    }
}
