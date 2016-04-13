package controller;


import domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import service.impl.UserServiceImpl;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@Component(value = "authBean")
@SessionScoped
public class AuthBin {

    private static final Logger LOG = LoggerFactory.getLogger(AuthBin.class);

    private UserServiceImpl userService;

    private User user;

    public AuthBin() {
        LOG.info("AuthBin created");
        user = new User();
        userService = new UserServiceImpl();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String doLogin() {

        User user1 = userService.getUserByLoginAndPassword(user.getLogin(), user.getPassword());

        if (user1 == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login or password", "incorrect"));
            return null;
        } else {
            user = user1;
            return "main";
        }
    }

}
