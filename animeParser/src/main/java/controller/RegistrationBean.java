package controller;

import domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import service.impl.AnimeServiceImpl;
import service.impl.FilmServiceImpl;
import service.impl.UserServiceImpl;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

@ManagedBean(name = "regBean")
@RequestScoped
@Lazy
public class RegistrationBean {

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationBean.class);

    @ManagedProperty("#{userServiceImpl}")
    private UserServiceImpl userService;

    @ManagedProperty("#{animeServiceImpl}")
    private AnimeServiceImpl animeService;

    @ManagedProperty("#{filmServiceImpl}")
    private FilmServiceImpl filmService;

    private UIComponent confirmpassword;

    private UIComponent login;

    private String name;

    private String surname;

    private String email;

    private String login_reg;

    private String password_reg;

    private String confirmPassword;

    public RegistrationBean() {
        LOG.info("Registration bean created");
    }

    public String registration() {
        if (password_reg.equals(confirmPassword)) {
            if (userService.getUserIdByLogin(login_reg) == null) {
                User user = new User(name,surname,email,login_reg,password_reg);
                userService.insertUser(user);
                Integer id = userService.getUserIdByLogin(login_reg);
                Integer lastAnimeId = animeService.getLastAnimeId();
                Integer lastFilmId = filmService.getLastFilmId();
                userService.setLastUpdateId(id, lastAnimeId-10, lastFilmId-10);
                return "auth";
            } else {
                FacesMessage msg =
                        new FacesMessage("Validation failed","Пользователь с таким логином уже существует");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage(login.getClientId(FacesContext.getCurrentInstance()), msg);
                return "registration";
            }
        } else {
            FacesMessage msg =
                    new FacesMessage("Validation failed","Пороли не совпадают");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(confirmpassword.getClientId(FacesContext.getCurrentInstance()), msg);
            return "registration";
        }
    }

    public void reset() {
        this.name = "";
        this.surname = "";
        this.email = "";
        this.login_reg = "";
        this.password_reg = "";
        this.confirmPassword = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword_reg() {
        return password_reg;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getLogin_reg() {
        return login_reg;
    }

    public void setLogin_reg(String login_reg) {
        this.login_reg = login_reg;
    }

    public void setPassword_reg(String password_reg) {
        this.password_reg = password_reg;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public UIComponent getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(UIComponent confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    public void setAnimeService(AnimeServiceImpl animeService) {
        this.animeService = animeService;
    }

    public void setFilmService(FilmServiceImpl filmService) {
        this.filmService = filmService;
    }

    public UIComponent getLogin() {
        return login;
    }

    public void setLogin(UIComponent login) {
        this.login = login;
    }
}
