package service.impl;

import domain.Anime;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

import java.util.List;

@Service(value = "userService")
public class UserServiceImpl {

    @Autowired
    UserService userService;

    public User getUserById(Integer id) {
        return userService.getUserById(id);
    }

    public List<Anime> getUsersSubscribedAnimeByUserId(Integer id) {
        return userService.getUsersSubscribedAnimeByUserId(id);
    }

    public User getUserByLoginAndPassword(String login, String password) {
        return userService.getUserByLoginAndPassword(login,password);
    }
}
