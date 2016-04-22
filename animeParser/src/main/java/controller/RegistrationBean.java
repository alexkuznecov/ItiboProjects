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

    private String login_reg;

    private String password_reg;

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
}
