package by.impl;


import by.domain.Anime;
import by.services.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "animeService")
public class AnimeServiceImpl {

    @Autowired
    AnimeService animeService;

    Anime getAnimeById(Integer id) {
        return animeService.getAnimeById(id);
    }

}
