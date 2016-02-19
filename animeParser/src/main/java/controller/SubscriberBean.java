package controller;

import domain.Anime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.SubscriberService;

import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@Component(value = "subscriber")
@ViewScoped
@Lazy
public class SubscriberBean {

    private static final Logger LOG = LoggerFactory.getLogger(SearchUpdate.class);

    List<Anime> subscribedAnime;

    @Autowired
    private SubscriberService subscriberService;

    @Autowired
    private AuthBin authBin;

    public SubscriberBean() {
        LOG.info("SubscriberBean created");
        subscribedAnime = new ArrayList<Anime>();
    }

    public List<Anime> getSubscribedAnime() {
        subscribedAnime = subscriberService.getUsersSubscribedAnimeByUserId(authBin.getUser().getId());
        return subscribedAnime;
    }

    public void setSubscribedAnime(List<Anime> subscribedAnime) {
        this.subscribedAnime = subscribedAnime;
    }

}
