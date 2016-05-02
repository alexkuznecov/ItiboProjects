package controller;

import domain.Film;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import service.impl.FilmServiceImpl;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import java.util.List;

@ManagedBean(name = "searchFilm")
@ViewScoped
@Lazy
public class SearchFilmBean {

    private static final Logger LOG = LoggerFactory.getLogger(SearchFilmBean.class);

    @ManagedProperty("#{filmServiceImpl}")
    private FilmServiceImpl filmService;

    @ManagedProperty("#{authBean}")
    private AuthBin authBin;

    private List<Film> resultFromPattern;

    private String message;

    public SearchFilmBean() {
        LOG.info("Search Bean Created");
        message = "";
    }

    public void setFilmService(FilmServiceImpl filmService) {
        this.filmService = filmService;
    }

    public void setAuthBin(AuthBin authBin) {
        this.authBin = authBin;
    }

    public List<Film> getResultFromPattern() {
        return resultFromPattern;
    }

    public void setResultFromPattern(List<Film> resultFromPattern) {
        this.resultFromPattern = resultFromPattern;
    }

    public void findByPattern(AjaxBehaviorEvent event) {
        resultFromPattern = filmService.getFilmByPattern(message + "%");
        if (message == "") {
            resultFromPattern = null;
        }
        System.out.println("Value cahnged");
    }

    public void findFilm() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
