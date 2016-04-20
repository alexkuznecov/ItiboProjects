package controller;

import domain.Anime;
import domain.SearchObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.impl.AnimeServiceImpl;
import service.impl.SearchServiceImpl;
import service.impl.SubscriberServiceImpl;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

@Component(value = "searchUpd")
@SessionScoped
@Lazy
public class SearchUpdate {

    private static final Logger LOG = LoggerFactory.getLogger(SearchUpdate.class);

    @Autowired
    private AuthBin authBin;

    @Autowired
    private SearchServiceImpl searchService;

    @Autowired
    private AnimeServiceImpl animeService;

    @Autowired
    private SubscriberServiceImpl subscriberService;


    public SearchUpdate() {
        LOG.info("SearchUpdate created");
    }

    public void updateInformation() throws IOException {
        LOG.info("updateInformation");

        SearchObject searchObject = new SearchObject();

        searchObject = searchService.getSearchObjectByUserId(-1);

        LOG.info(searchObject.getName() + " " + searchObject.getDate());

    }

    public String subscribeForAnime(Anime anime) {

        Integer animeId = animeService.getAnimeIdByName(anime.getName());

        if (animeId == null) {
            animeService.insertAnime(anime.getName(), anime.getPublicationDate(), anime.getSite(), authBin.getUser().getId());
            animeId = animeService.getAnimeIdByName(anime.getName());
        }

        Integer subscribeId = subscriberService.getSubscribeIdByUserIdAndAnimeId(authBin.getUser().getId(),animeId);

        if (subscribeId == null) {
            subscriberService.insertSubscriber(authBin.getUser().getId(),animeId);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Subscribed", "successful"));
            LOG.info("Subscribed succesfull");
            return null;
        } else {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "You already subscribed", "on this anime"));
            LOG.info("You already subscribed");
            return null;
        }
    }
}
