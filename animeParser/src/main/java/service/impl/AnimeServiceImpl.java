package service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AnimeService;

@Service(value = "animeService")
public class AnimeServiceImpl {

    @Autowired
    AnimeService animeService;

    public Integer getAnimeIdByName(String name) {
        return animeService.getAnimeIdByName(name);
    }

    public void insertAnime(String name, String publicationDate, String site, Integer user_id) {
        animeService.insertAnime(name,publicationDate, site, user_id);
    }


}
