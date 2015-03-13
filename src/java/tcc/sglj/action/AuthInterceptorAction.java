/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.sglj.action;

import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.ActionInvocation;

import tcc.sglj.model.User;
       
/**
 * @author jhonatan
 */
public class AuthInterceptorAction implements Interceptor {
    public String intercept(ActionInvocation invocation) throws Exception {
        User user = (User) invocation.getInvocationContext().getSession().get("user");
        
        if(user == null) {
            return "not_auth";
        }
        return invocation.invoke();
    }
    
    public void init(){}
    public void destroy(){}
    
}

