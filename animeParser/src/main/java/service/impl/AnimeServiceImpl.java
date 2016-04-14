package service.impl;


import service.AnimeService;

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


}
