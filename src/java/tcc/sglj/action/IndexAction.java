/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.sglj.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author jhonatan
 */
@Namespace(value = "/index")
public class IndexAction {
    
    @Action(value = "index", results = {
        @Result(name = "success", location = "/view/index/index.jsp")
    })
    public String index(){
        return "success";
    }
}
