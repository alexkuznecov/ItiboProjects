package controller;

import domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import service.impl.UserServiceImpl;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "profileBean")
@RequestScoped
@Lazy
public class ProfileBean {

    private static final Logger LOG = LoggerFactory.getLogger(ProfileBean.class);

    @ManagedProperty("#{authBean}")
    private AuthBin authBin;

    @ManagedProperty("#{userServiceImpl}")
    private UserServiceImpl userService;

    private User userProfile;

    public ProfileBean() {
        LOG.info("Profile bean created");
        userProfile = new User();
    }

    public String getInfo() {
        userProfile = userService.getUserById(authBin.getUser().getId());
        return "profile";
    }

    public User getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(User userProfile) {
        this.userProfile = userProfile;
    }

    public void setAuthBin(AuthBin authBin) {
        this.authBin = authBin;
    }

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
}
