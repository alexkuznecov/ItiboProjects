package by.impl;

import by.domain.Film;
import by.domain.FilmTemplate;
import by.services.FilmService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilmServiceImpl implements FilmService {


    @Autowired
    private FilmService filmService;

    @Override
    public void insertFilm(String name, Integer year, String quality, String sound, String site) {
        filmService.insertFilm(name,year,quality,sound,site);
    }

    @Override
    public Film getFilmByNameAndYear(String name, Integer year) {
        return filmService.getFilmByNameAndYear(name,year);
    }

    @Override
    public void updateFilmById(String name, Integer year, String quality, String sound, String site, Integer id) {
        filmService.updateFilmById(name,year,quality,sound,site,id);
    }

    @Override
    public List<FilmTemplate> getFilmForSearch() {
        return filmService.getFilmForSearch();
    }

    @Override
    public void updateSearchFilmFound(@Param(value = "id") Integer id) {
        filmService.updateSearchFilmFound(id);
    }

    @Override
    public void updateSubscribedFilm(@Param(value = "filmId") Integer filmId) {

    }
}
