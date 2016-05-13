package controller;

import domain.Anime;
import domain.Film;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import service.impl.SubscriberServiceImpl;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "subscribe")
@ViewScoped
@Lazy
public class SubscribeBean {

    private static final Logger LOG = LoggerFactory.getLogger(SubscribeBean.class);

    @ManagedProperty("#{authBean}")
    private AuthBin authBin;

    @ManagedProperty("#{subscriberServiceImpl}")
    private SubscriberServiceImpl subscriberService;

    private List<Anime> subscribedAnime;

    private List<Film> subscribedFilms;

    private String selected;

    private List<String> selectedChoise;

    public SubscribeBean() {
        LOG.info("Subscribe bean created");
        selectedChoise = new ArrayList<String>();
        selectedChoise.add("anime");
        selectedChoise.add("film");
        selectedChoise.add("updatedanime");
        selectedChoise.add("updatedfilm");
    }

    public void update(ValueChangeEvent e) {
        if (e.getNewValue().equals("anime")) {
            updateAnime();
        } else if (e.getNewValue().equals("film")) {
            updateFilm();
        } else if (e.getNewValue().equals("updatedanime")) {
            updatedAnimeUpdatedValues();
        } else if (e.getNewValue().equals("updatedfilm")){
            updatedFilmUpdatedValues();
        } else if (e.getNewValue().equals("select")) {
            subscribedAnime = null;
            subscribedFilms = null;
        }
    }

    public void updateAnime() {
        subscribedAnime = subscriberService.getUsersSubscribedAnimeByUserId(authBin.getUser().getId());
        if (subscribedAnime.size() == 0) {
            Anime anime = new Anime();
            anime.setName("Обновлений нет");
            subscribedAnime.add(anime);
        }
    }

    public void updateFilm() {
        subscribedFilms = subscriberService.getUsersSubscribedFilmByUserId(authBin.getUser().getId());
        if (subscribedFilms.size() == 0) {
            Film film = new Film();
            film.setName("Обновлений нет");
            subscribedFilms.add(film);
        }
    }

    public void updatedAnimeUpdatedValues() {
        subscribedAnime = subscriberService.getUsersSubscribedAnimeByUserIdIfUpdated(authBin.getUser().getId());
        if (subscribedAnime.size() == 0) {
            Anime anime = new Anime();
            anime.setName("Обновлений нет");
            subscribedAnime.add(anime);
        }
    }

    public void updatedFilmUpdatedValues() {
        subscribedFilms = subscriberService.getUsersSubscribedFilmByUserIdIfUpdated(authBin.getUser().getId());
        if (subscribedFilms.size() == 0) {
            Film film = new Film();
            film.setName("Обновлений нет");
            subscribedFilms.add(film);
        }
    }

    public void resetUpdates() {
        subscriberService.updateReadedInfo(authBin.getUser().getId());
    }

    public List<Anime> getSubscribedAnime() {
        return subscribedAnime;
    }

    public void setSubscribedAnime(List<Anime> subscribedAnime) {
        this.subscribedAnime = subscribedAnime;
    }

    public List<Film> getSubscribedFilms() {
        return subscribedFilms;
    }

    public void setSubscribedFilms(List<Film> subscribedFilms) {
        this.subscribedFilms = subscribedFilms;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public List<String> getSelectedChoise() {
        return selectedChoise;
    }

    public void setSelectedChoise(List<String> selectedChoise) {
        this.selectedChoise = selectedChoise;
    }

    public void setAuthBin(AuthBin authBin) {
        this.authBin = authBin;
    }

    public void setSubscriberService(SubscriberServiceImpl subscriberService) {
        this.subscriberService = subscriberService;
    }
}
