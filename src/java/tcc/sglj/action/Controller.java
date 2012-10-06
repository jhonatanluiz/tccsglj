/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.sglj.action;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;

import tcc.sglj.model.User;

/**
 *
 * @author jhonatan
 */
public class Controller {

    private String action;
    private String message;
    private boolean success;
    
    protected User getCurrentUser() {
        Object o = ServletActionContext.getContext().getSession().get("user");
        if(o != null) {
            return (User)o;
        }
        return null;
    }
    
    protected void setCurrentUser(User user) {
        ServletActionContext.getContext().getSession().put("user", user);
    }

    public void setHelpMessage(boolean success, String type, String message) {
        this.setSuccess(success);
        StringBuilder div = new StringBuilder();

        if (type.equals("alert")) {
            div.append("<div class=\"alert fade in ");
            if (success) {
                div.append("alert-success\">");
            } else {
                div.append("alert-error\">");
            }
            div.append("<button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>");
            div.append(message);
            div.append("</div>");
        } else {
            div.append(message);
        }

        this.setMessage(div.toString());
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }
}
