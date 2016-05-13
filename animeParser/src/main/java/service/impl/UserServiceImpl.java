package service.impl;

import domain.Anime;
import domain.User;
import service.UserService;

import java.util.List;

public class UserServiceImpl {

    UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void insertUser(User user) {
        userService.insertUser(user.getName(), user.getSurname(),user.getEmail(),user.getLogin(),user.getPassword());
    }

    public User getUserById(Integer id) {
        return userService.getUserById(id);
    }

    public List<Anime> getUsersSubscribedAnimeByUserId(Integer id) {
        return userService.getUsersSubscribedAnimeByUserId(id);
    }

    public User getUserByLoginAndPassword(String login, String password) {

        return userService.getUserByLoginAndPassword(login,password);
    }

    public void setLastUpdateId(Integer userId, Integer animeId, Integer filmId) {
        userService.setLastUpdateId(userId, animeId, filmId);
    }

    public Integer getUserIdByLogin(String login) {
        return userService.getUserIdByLogin(login);
    }
}
