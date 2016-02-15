package service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.SubscriberService;

@Service(value = "subscriberService")
public class SubscriberServiceImpl {

    @Autowired
    SubscriberService subscriberService;

    public Integer getSubscribeIdByUserIdAndAnimeId(Integer user_id, Integer anime_id) {
        return subscriberService.getSubscribeIdByUserIdAndAnimeId(user_id, anime_id);
    }

    public void insertSubscriber(Integer user_id, Integer anime_id) {
        subscriberService.insertSubscriber(user_id,anime_id);
    }
}
