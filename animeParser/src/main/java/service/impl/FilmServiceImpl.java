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
}
