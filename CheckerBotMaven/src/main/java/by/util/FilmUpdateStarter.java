package by.util;

import by.domain.Film;
import by.domain.SearchObject;
import by.services.FilmService;
import by.services.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilmUpdateStarter {

    private static final Logger LOG = LoggerFactory.getLogger(AnimeUpdateStarter.class);

    private FilmService filmService;

    private SearchService searchService;

    public FilmUpdateStarter(FilmService filmService, SearchService searchService) {
        this.filmService = filmService;
        this.searchService = searchService;
    }

    public void startUpdate() {
        LOG.warn("Film update started");
        SearchObject lastSearchFilm = searchService.getSearchObjectByUserId(-2);

        LOG.warn("Kinogo.co : ");
        KinogoParser kinogoParser = new KinogoParser();
        try {
            kinogoParser.getUpdateFromSite(lastSearchFilm.getName(), Integer.parseInt(lastSearchFilm.getDate()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!kinogoParser.getNewFilm().isEmpty()) {
            int size = kinogoParser.getNewFilm().size() - 1;
            searchService.updateLastSearchObject(-2,kinogoParser.getNewFilm().get(size).getName(),
                    Integer.toString(kinogoParser.getNewFilm().get(size).getYear()));
        }
        LOG.warn("Kinogo.co finished.");

        lastSearchFilm = searchService.getSearchObjectByUserId(-3);
        LOG.warn("Bobfilm1.net");
        BobfilmParser bobfilmParser = new BobfilmParser();
        try {
            bobfilmParser.getUpdateFromSite(lastSearchFilm.getName(), Integer.parseInt(lastSearchFilm.getDate()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!bobfilmParser.getNewFilm().isEmpty()) {
            int size = bobfilmParser.getNewFilm().size() - 1;
            searchService.updateLastSearchObject(-3,bobfilmParser.getNewFilm().get(size).getName(),
                    Integer.toString(bobfilmParser.getNewFilm().get(size).getYear()));
        }
        LOG.warn("Kinogo.co finished.");
        LOG.warn("Bobfilm1.net finished.");

        List<Film> allUpdateFilm = new ArrayList<Film>(kinogoParser.getNewFilm());
        allUpdateFilm.addAll(bobfilmParser.getNewFilm());
        Film searchInDatabase = new Film();
        for (int i=0; i<allUpdateFilm.size(); i++) {
            searchInDatabase = filmService.getFilmByNameAndYear(allUpdateFilm.get(i).getName(), allUpdateFilm.get(i).getYear());
            if (searchInDatabase != null) {
                if (searchInDatabase.getQuality().getValue() < allUpdateFilm.get(i).getQuality().getValue()) {
                    filmService.updateFilmById(allUpdateFilm.get(i).getName(), allUpdateFilm.get(i).getYear(),
                            allUpdateFilm.get(i).getQuality().getName(), allUpdateFilm.get(i).getSound(),
                            allUpdateFilm.get(i).getSite(), allUpdateFilm.get(i).getId());
                }
            } else {
                filmService.insertFilm(allUpdateFilm.get(i).getName(), allUpdateFilm.get(i).getYear(),
                        allUpdateFilm.get(i).getQuality().getName(), allUpdateFilm.get(i).getSound(),
                        allUpdateFilm.get(i).getSite());
            }
            try {
                Film film = filmService.getFilmByNameAndYear(allUpdateFilm.get(i).getName(), allUpdateFilm.get(i).getYear());
                filmService.updateSubscribedFilm(film.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        LOG.warn("Film update finished");
    }

}
