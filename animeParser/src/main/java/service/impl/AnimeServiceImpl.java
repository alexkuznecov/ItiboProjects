package service.impl;


import domain.Anime;
import service.AnimeService;

import java.util.List;

public class AnimeServiceImpl {

    AnimeService animeService;

    public void setAnimeService(AnimeService animeService) {
        this.animeService = animeService;
    }

    public Integer getAnimeIdByName(String name) {
        return animeService.getAnimeIdByName(name);
    }

    public void insertAnime(String name, String publicationDate, String site, Integer user_id) {
        animeService.insertAnime(name,publicationDate, site, user_id);
    }

    public List<Anime> getAnimeByBeginId(Integer id) {
        return animeService.getAnimeByBeginId(id);
    }

    public  Integer getLastAnimeId() {
        return animeService.getLastAnimeId();
    }

    public Integer getRelationIdIfSubscribed(Integer userId, String animeName) {
        return animeService.getRelationIdIfSubscribed(userId, animeName);
    }

    public void subscribeUser(Integer userId, String animeName, Integer animId) {
        animeService.subscribeUser(userId, animeName, animId);
    }
}
