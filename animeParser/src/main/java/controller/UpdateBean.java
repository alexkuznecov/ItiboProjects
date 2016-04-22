package controller;

import domain.Anime;
import domain.Film;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.faces.bean.ViewScoped;
import java.util.List;

@Component(value = "updateBean")
@ViewScoped
@Lazy
public class UpdateBean {

    private static final Logger LOG = LoggerFactory.getLogger(UpdateBean.class);

    private List<Anime> updatesAnime;

    private List<Film> updatesFilms;

    private int selectedMenu;

    public UpdateBean() {
        LOG.info("Update bean created");
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

    public int getSelectedMenu() {
        return selectedMenu;
    }

    public void setSelectedMenu(int selectedMenu) {
        this.selectedMenu = selectedMenu;
    }
}
