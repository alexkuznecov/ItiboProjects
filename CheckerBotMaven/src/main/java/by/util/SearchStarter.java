package by.util;

import by.domain.Film;
import by.domain.FilmTemplate;
import by.services.FilmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class SearchStarter {

    private static final Logger LOG = LoggerFactory.getLogger(SearchStarter.class);

    private FilmService filmService;

    private List<FilmTemplate> filmsForSearch;

    public SearchStarter(FilmService filmService) {
        this.filmService = filmService;
    }

    public void searchFilms() {
        filmsForSearch = filmService.getFilmForSearch();
        if (filmsForSearch.size() != 0) {
            for (int i = 0; i < filmsForSearch.size(); i++) {
                SearchResultParser searchResultParser = new SearchResultParser();
                try {
                    searchResultParser.searchFilmForName(filmsForSearch.get(i).getFilmName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Film searchInDatabase = new Film();
                for (int j = 0; j < searchResultParser.getFoundedFilms().size(); j++) {
                    searchInDatabase = filmService.getFilmByNameAndYear(searchResultParser.getFoundedFilms().get(j).getName(), searchResultParser.getFoundedFilms().get(j).getYear());
                    if (searchInDatabase != null) {
                        if (searchInDatabase.getQuality().getValue() < searchResultParser.getFoundedFilms().get(j).getQuality().getValue()) {
                            filmService.updateFilmById(searchResultParser.getFoundedFilms().get(j).getName(), searchResultParser.getFoundedFilms().get(j).getYear(),
                                    searchResultParser.getFoundedFilms().get(j).getQuality().getName(), searchResultParser.getFoundedFilms().get(j).getSound(),
                                    searchResultParser.getFoundedFilms().get(j).getSite(), searchResultParser.getFoundedFilms().get(j).getId());
                            LOG.info("UPDATED");
                        }
                        LOG.info("SKIPPED");
                    } else {
                        filmService.insertFilm(searchResultParser.getFoundedFilms().get(j).getName(), searchResultParser.getFoundedFilms().get(j).getYear(),
                                searchResultParser.getFoundedFilms().get(j).getQuality().getName(), searchResultParser.getFoundedFilms().get(j).getSound(),
                                searchResultParser.getFoundedFilms().get(j).getSite());
                        LOG.info("INSERTED");
                    }
                }

                if (searchResultParser.getFoundedFilms().size() != 0) {
                    filmService.updateSearchFilmFound(filmsForSearch.get(i).getId());
                }
            }
        }
    }

    public List<FilmTemplate> getFilmsForSearch() {
        return filmsForSearch;
    }

    public void setFilmsForSearch(List<FilmTemplate> filmsForSearch) {
        this.filmsForSearch = filmsForSearch;
    }
}
