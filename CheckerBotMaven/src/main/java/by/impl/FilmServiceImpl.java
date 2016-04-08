package by.impl;

import by.domain.Film;
import by.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;


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
}
