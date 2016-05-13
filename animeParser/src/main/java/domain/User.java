package domain;

import java.util.List;

public class User {

    private Integer id;

    private String name;

    private String surname;

    private String email;

    private String login;

    private String password;

    private List<Anime> userSubscribedAnime;

    private List<Film> userSubscribedFilm;

    public User() {

    }

    public User(String name, String surname, String email, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.login = login;
        this.password = password;
    }

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

    public List<Film> getUserSubscribedFilm() {
        return userSubscribedFilm;
    }

    public void setUserSubscribedFilm(List<Film> userSubscribedFilm) {
        this.userSubscribedFilm = userSubscribedFilm;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
