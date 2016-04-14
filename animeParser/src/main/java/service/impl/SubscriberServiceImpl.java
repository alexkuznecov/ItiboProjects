package service.impl;


import domain.Anime;
import service.SubscriberService;

import java.util.List;

public class SubscriberServiceImpl {

    SubscriberService subscriberService;

    public void setSubscriberService(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    public Integer getSubscribeIdByUserIdAndAnimeId(Integer user_id, Integer anime_id) {
        return subscriberService.getSubscribeIdByUserIdAndAnimeId(user_id, anime_id);
    }

    public void insertSubscriber(Integer user_id, Integer anime_id) {
        subscriberService.insertSubscriber(user_id,anime_id);
    }

    public List<Anime> getUsersSubscribedAnimeByUserId(Integer id) {
        return subscriberService.getUsersSubscribedAnimeByUserId(id);
    }
}
