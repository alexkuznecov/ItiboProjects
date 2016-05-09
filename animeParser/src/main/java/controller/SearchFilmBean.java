package controller;

import domain.Film;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import service.impl.FilmServiceImpl;
import util.SpellerUtility;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import java.io.IOException;
import java.util.ArrayList;
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

    private String userInputCorrected;

    private boolean renderMessageShowResult;

    public SearchFilmBean() {
        LOG.info("Search Bean Created");
        message = "";
        renderMessageShowResult = false;
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
        userInputCorrected = "";
        System.out.println("Value cahnged");
    }

    public void findFilm() throws IOException {
        String prefix = "Также показаны результаты для : ";
        List<String> userInputRight = SpellerUtility.getCheckedResult(message);
        resultFromPattern = new ArrayList<Film>();
        for (int i = 0; i < userInputRight.size(); i++) {
            resultFromPattern.addAll(filmService.getFilmByPattern(userInputRight.get(i)));
        }
        if (userInputRight.size() > 1) {
            userInputCorrected = prefix;
            userInputCorrected += userInputRight.get(1);
            renderMessageShowResult = true;
        }

        if (resultFromPattern.size() == 0) {
            renderMessageShowResult = true;
            prefix = "К сожелению, ничего не найдено. Попробуйте позже";
            userInputCorrected = prefix;

        }
    }

    public void subscribe(int id) {
        if (filmService.getRelationIdIfSubscribed(authBin.getUser().getId(), id) == null) {
            filmService.subscribeUser(authBin.getUser().getId(), id);
            LOG.info("Subscribe");
            FacesMessage facesMessage = new FacesMessage("Подписка офромлена");
            FacesContext.getCurrentInstance().addMessage("searchForm:inputFilmName", facesMessage);
        } else {
            FacesMessage facesMessage = new FacesMessage("Вы уже подписались на этот фильм");
            FacesContext.getCurrentInstance().addMessage("searchForm:inputFilmName", facesMessage);
            LOG.info("Already subscribed");
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserInputCorrected() {
        return userInputCorrected;
    }

    public void setUserInputCorrected(String userInputCorrected) {
        this.userInputCorrected = userInputCorrected;
    }

    public boolean isRenderMessageShowResult() {
        return renderMessageShowResult;
    }

    public void setRenderMessageShowResult(boolean renderMessageShowResult) {
        this.renderMessageShowResult = renderMessageShowResult;
    }
}
