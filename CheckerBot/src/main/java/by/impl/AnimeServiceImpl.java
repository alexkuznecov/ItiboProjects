package by.impl;


import by.services.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "animeService")
public class AnimeServiceImpl {

    @Autowired
    AnimeService animeService;

    public void insertAnime(String name, String publicationDate, String site, String newSeries) {
        animeService.insertAnime(name, publicationDate, site, newSeries);
    }

}
