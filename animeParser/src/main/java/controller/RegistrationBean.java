package controller;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.faces.bean.RequestScoped;

@Component(value = "regBean")
@RequestScoped
@Lazy
public class RegistrationBean {

    private String name;

    private String surname;

    private String email;

    private String login;

    private String password;

    private String confirmPassword;

    public RegistrationBean() {

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

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
