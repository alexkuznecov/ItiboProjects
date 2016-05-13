package service.impl;


import domain.Anime;
import domain.Film;
import service.SubscriberService;

import java.util.List;

public class SubscriberServiceImpl {

    SubscriberService subscriberService;

    public void setSubscriberService(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    public List<Anime> getUsersSubscribedAnimeByUserId(Integer id) {
        return subscriberService.getUsersSubscribedAnimeByUserId(id);
    }

    public List<Anime> getUsersSubscribedAnimeByUserIdIfUpdated(Integer id) {
        return subscriberService.getUsersSubscribedAnimeByUserIdIfUpdated(id);
    }

    public List<Film> getUsersSubscribedFilmByUserId(Integer id) {
        return subscriberService.getUsersSubscribedFilmByUserId(id);
    }

    public List<Film> getUsersSubscribedFilmByUserIdIfUpdated(Integer id) {
        return subscriberService.getUsersSubscribedFilmByUserIdIfUpdated(id);
    }
}
