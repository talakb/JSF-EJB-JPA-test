package edu.mum.cs.cs490.mbean;

import edu.mum.cs.cs490.login.service.UserService;
import edu.mum.cs.cs490.login.service.entity.MyComapnyUser;
import edu.mum.cs.cs490.mycomp.util.PasswordService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TalakB User bean
 */
@Named
@SessionScoped
public class UserBean implements Serializable {

    @EJB
    UserService userservice;
    MyComapnyUser user = new MyComapnyUser();

    private PasswordService encpass = new PasswordService();

    public UserBean() {
        user = new MyComapnyUser();
    }

    public MyComapnyUser getUser() {
        return user;
    }

    public void setUser(MyComapnyUser user) {
        this.user = user;
    }

    public String addCustomer() throws Exception {
        String encPass = encryptUserPassword(user.getPassword());
        user.setPassword(encPass);
        if (userservice.saveUser(user)) {
            return "registration_confirmation";
        }
        return null;
    }

    public String loginUser() throws Exception {
        String encPass = encryptUserPassword(user.getPassword());
        user.setPassword(encPass);
        if (userservice.authenticateUser(user)) {
            return "user_home";
        }
        return "";
    }

    public String registerUser() {
        return "register_user";
    }

    public String encryptUserPassword(String password) throws Exception {
        String encrptedPass = encpass.encrypt(user.getPassword());
        return encrptedPass;
    }

    /**
     * Logout user -Invalidate the session and redirect to home page 
     * @return 
     */
    public String logoutUser() {
        HttpSession activeSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        activeSession.invalidate();
        return "index";
    }

}
