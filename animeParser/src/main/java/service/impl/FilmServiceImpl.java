package service.impl;

import domain.Film;
import service.FilmService;

import java.util.List;

public class FilmServiceImpl {

    FilmService filmService;

    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }

    public List<Film> getFilmByBeginId(Integer id) {
        return filmService.getFilmByBeginId(id);
    }

    public List<Film> getFilmByPattern(String pattern) {
        return filmService.getFilmByPattern(pattern);
    }

    public Integer getRelationIdIfSubscribed(Integer userId, Integer filmId) {
        return filmService.getRelationIdIfSubscribed(userId, filmId);
    }

    public void subscribeUser(Integer userId, Integer filmId) {
        filmService.subscribeUser(userId,filmId);
    }

    public void insertFilmForSearch(String filmName, Integer userId) {
        filmService.insertFilmForSearch(filmName, userId);
    }
}
