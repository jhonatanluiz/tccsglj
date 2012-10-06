/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.sglj.action;

import java.util.Date;
import java.util.List;
import java.util.Iterator;
import java.util.ResourceBundle;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.ServletActionContext;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tcc.sglj.model.User;
import tcc.sglj.model.LostPassword;
import tcc.sglj.util.Encrypt;
import tcc.sglj.app.Authenticate;
import tcc.sglj.dao.DaoUser;
import tcc.sglj.util.SystemMessage;
import tcc.sglj.util.mail.EmailMessage;
import tcc.sglj.util.mail.Email;
import tcc.sglj.util.mail.EmailException;
import tcc.sglj.dao.DaoHibernate;

/**
 *
 * @author jhonatan
 */
@Namespace(value = "/authenticate")
@ParentPackage(value = "default")
public class AuthAction extends Controller {

    private User user;
    private String key;
    private String npassword;
    private String cpassword;
    private String apassword;
    private DaoUser dao;

    public AuthAction() {
        this.dao = new DaoUser();
    }

    @Action(value = "/login", results = {
        @Result(name = "success", location = "/view/authenticate/login.jsp")
    })
    public String login() {
        return "success";
    }

    @Action(value = "/lostPassword", results = {
        @Result(name = "success", location = "/view/authenticate/lostPassword.jsp")
    })
    public String lostPassword() {
        return "success";
    }

    @Action(value = "/authenticate", results = {
        @Result(name="success", location="/panel/panel", type="redirect"),
        @Result(name = "error", type = "redirectAction", params = {
            "actionName", "login", "namespace", "authenticate", "message", "%{message}"
        })
    })
    public String authenticate() {

        try {

            if (getUser().getPassword().isEmpty() || getUser().getEmail().isEmpty()) {
                throw new NullPointerException();
            }

            getUser().setPassword(new Encrypt(getUser().getPassword()).encrypt());
            User valid = Authenticate.authentic(getUser());

            if (valid != null) {
                ServletActionContext.getContext().getSession().put("user", valid);
            } else {
                setHelpMessage(false, "alert", SystemMessage.get("MSG002"));
                return "error";
            }
        } catch (NullPointerException ex) {
            setHelpMessage(false, "alert", SystemMessage.get("MSG003"));
            return "error";
        } catch (Exception e) {
            setHelpMessage(false, "alert", SystemMessage.get("MSG007"));
            return "error";
        }
        return "success";
    }

    @Action(value = "/logout", results = {
        @Result(name = "success", location = "/index", type = "redirect")
    })
    public String logout() {
        ServletActionContext.getContext().getSession().remove("user");
        return "success";
    }

    @Action(value = "/requestPassword", results = {
        @Result(name = "success", location = "/view/authenticate/lostPassword.jsp"),
        @Result(name = "error", location = "/view/authenticate/lostPassword.jsp")
    })
    public String requestPassword() {
        Session session = getDao().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            String s = getUser().getEmail();
            if (getUser() != null && getUser().getEmail() == null) {
                String m = "erro aqui!";
                throw new NullPointerException();
            }

            User u = getDao().validEmail(getUser().getEmail());

            if (u != null) {


                if (u.getLostPassword() != null) {
                    Iterator iterator = u.getLostPassword().iterator();
                    while (iterator.hasNext()) {
                        LostPassword current = (LostPassword) iterator.next();
                        current.setActive(false);
                        current.setDateAlter(new Date());
                        session.update(current);
                    }
                }

                LostPassword password = new LostPassword();
                password.setKey(new Encrypt(u.getPassword() + Math.random() * 100).encrypt());
                password.setActive(true);
                password.setDateExpire(new Date());
                password.setUser(u);


                String url = "http://localhost:8084"
                        + ServletActionContext.getRequest().getContextPath()
                        + "/authenticate/resetPassword?key="
                        + password.getKey();

                String msg = SystemMessage.get("MSG008").replace("<URL>", url);
                EmailMessage message = new EmailMessage();
                message.setTo(u.getEmail());
                message.setSubject("teste");
                message.setFrom(ResourceBundle.getBundle("email").getString("mail.from"));
                message.setText(msg);

                Email email = new Email(ResourceBundle.getBundle("email"));
                email.send(message);

                session.save(password);
                transaction.commit();

                setHelpMessage(true, "alert", SystemMessage.get("MSG005"));
            } else {
                setHelpMessage(false, "alert", SystemMessage.get("MSG006"));
                return "error";
            }

        } catch (NullPointerException nullable) {
            transaction.rollback();
            setHelpMessage(false, "alert", SystemMessage.get("MSG004"));
            return "error";
        } catch (EmailException email) {
            transaction.rollback();
            setHelpMessage(false, "alert", email.getMessage());
            return "error";
        } catch (Exception e) {
            transaction.rollback();
            setHelpMessage(false, "alert", SystemMessage.get("MSG003"));
            return "error";
        }
        return "success";
    }

    @Action(value = "/resetPassword", results = {
        @Result(name = "success", location = "/view/authenticate/resetPassword.jsp"),
        @Result(name = "error", location = "/view/authenticate/resetPassword.jsp")
    })
    public String resetPassword() {
        try {
            if (getKey() == null) {
                throw new Exception(SystemMessage.get("MSG010"));
            }

            LostPassword lost = (LostPassword) getDao().fetch(LostPassword.class, "key", getKey());

            if (!lost.isActive()) {
                throw new Exception(SystemMessage.get("MSG010"));
            }

            if (getAction() != null && getAction().equals("password")) {

                if (getNpassword() == null || getCpassword() == null) {
                    throw new Exception(SystemMessage.get("MSG012"));
                }
                if (getNpassword().equals(getCpassword())) {

                    User u = lost.getUser();
                    u.setPassword(new Encrypt(getNpassword()).encrypt());
                    u.setStatus(true);
                    u.setEmail(u.getEmail().trim());
                    lost.setActive(false);
                    lost.setDateAlter(new Date());

                    getDao().getSession().getTransaction().begin();

                    getDao().save(u);
                    getDao().getSession().update(lost);

                    getDao().getSession().flush();
                    getDao().getSession().getTransaction().commit();
                    setHelpMessage(true, "alert", SystemMessage.get("MSG013"));
                } else {
                    throw new Exception(SystemMessage.get("MSG011"));
                }
            }
        } catch (Exception e) {
            setHelpMessage(false, "alert", e.getMessage());
            return "error";
        }
        return "success";
    }

    @Action(value = "/changePassword", results = {
        @Result(name = "success", type = "redirectAction", params = {
            "actionName", "user/panel", "message", "%{message}"
        }),
        @Result(name = "error", type = "redirectAction", params = {
            "actionName", "user/panel", "message", "%{message}"
        }),
        @Result(name = "user_error", type = "redirectAction", params = {
            "actionName", "authenticate/authenticate", "message", "%{message}"
        })
    })
    public String changePassword() {
        try {
            if (getCurrentUser() == null) {
                return "user_error";
            }

            if (!getNpassword().equals(getCpassword())) {
                throw new Exception(SystemMessage.get("MSG011"));
            }

            String nova = new Encrypt(getApassword()).encrypt();
            String atual = getCurrentUser().getPassword().trim();

            if (!atual.equals(nova)) {
                throw new Exception(SystemMessage.get("MSG016"));
            }


            Session session = getDao().getSession();
            User u = (User) getDao().fetch(User.class, "id", getCurrentUser().getId());
            u.setPassword(new Encrypt(getNpassword()).encrypt());
            setCurrentUser(u);
            Transaction transaction = session.beginTransaction();

            session.flush();
            session.saveOrUpdate(getCurrentUser());

            transaction.commit();

            setHelpMessage(true, "alert", SystemMessage.get("MSG017"));

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
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the npassword
     */
    public String getNpassword() {
        return npassword;
    }

    /**
     * @param npassword the npassword to set
     */
    public void setNpassword(String npassword) {
        this.npassword = npassword;
    }

    /**
     * @return the cpassword
     */
    public String getCpassword() {
        return cpassword;
    }

    /**
     * @param cpassword the cpassword to set
     */
    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }

    /**
     * @return the apassword
     */
    public String getApassword() {
        return apassword;
    }

    /**
     * @param apassword the apassword to set
     */
    public void setApassword(String apassword) {
        this.apassword = apassword;
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
}