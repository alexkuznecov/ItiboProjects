package domain;

import java.util.List;

public class User {

    private Integer id;

    private String name;

    private String surname;

    private String login;

    private String password;

    private List<Anime> userSubscribedAnime;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setUserSubscribedAnime(List<Anime> userSubscribedAnime) {
        this.userSubscribedAnime = userSubscribedAnime;
    }

    public List<Anime> getUserSubscribedAnime() {
        return userSubscribedAnime;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
