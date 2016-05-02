package controller;

import domain.Anime;
import domain.Film;
import domain.LastUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import service.impl.AnimeServiceImpl;
import service.impl.FilmServiceImpl;
import service.impl.SearchServiceImpl;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "updateBean")
@ViewScoped
@Lazy
public class UpdateBean {

    private static final Logger LOG = LoggerFactory.getLogger(UpdateBean.class);

    @ManagedProperty("#{searchServiceImpl}")
    private SearchServiceImpl searchService;

    @ManagedProperty("#{animeServiceImpl}")
    private AnimeServiceImpl animeService;

    @ManagedProperty("#{filmServiceImpl}")
    private FilmServiceImpl filmService;

    @ManagedProperty("#{authBean}")
    private AuthBin authBin;

    private List<Anime> updatesAnime;

    private List<Film> updatesFilms;

    private String selected;

    private List<String> selectedChoise;

    public UpdateBean() {
        LOG.info("Update bean created");
        selectedChoise = new ArrayList<String>();
        selectedChoise.add("anime");
        selectedChoise.add("film");
    }

    public List<Anime> getUpdatesAnime() {
        return updatesAnime;
    }

    public void setUpdatesAnime(List<Anime> updatesAnime) {
        this.updatesAnime = updatesAnime;
    }

    public List<Film> getUpdatesFilms() {
        return updatesFilms;
    }

    public void setUpdatesFilms(List<Film> updatesFilms) {
        this.updatesFilms = updatesFilms;
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

    public void setSearchService(SearchServiceImpl searchService) {
        this.searchService = searchService;
    }

    public void setAnimeService(AnimeServiceImpl animeService) {
        this.animeService = animeService;
    }

    public void setFilmService(FilmServiceImpl filmService) {
        this.filmService = filmService;
    }

    public void setAuthBin(AuthBin authBin) {
        this.authBin = authBin;
    }

    public void update() {
        LOG.info("Started" + authBin.getUser().getId());
        LastUpdate lastUpdate = searchService.getLastUpdatesIdByUserId(authBin.getUser().getId());
        updatesAnime = animeService.getAnimeByBeginId(lastUpdate.getAnimeId());
        updatesFilms = filmService.getFilmByBeginId(lastUpdate.getFilmId());
    }
}
