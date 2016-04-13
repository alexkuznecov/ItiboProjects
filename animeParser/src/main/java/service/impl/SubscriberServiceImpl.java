package service.impl;


import domain.Anime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.SubscriberService;

import java.util.List;

@Component
public class SubscriberServiceImpl {

    @Autowired
    SubscriberService subscriberService;

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
