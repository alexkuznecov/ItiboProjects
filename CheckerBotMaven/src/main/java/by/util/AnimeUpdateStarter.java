package by.util;

import by.domain.SearchObject;
import by.services.AnimeService;
import by.services.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class AnimeUpdateStarter {

    private static final Logger LOG = LoggerFactory.getLogger(AnimeUpdateStarter.class);

    private AnimeService animeService;

    private SearchService searchService;

    public AnimeUpdateStarter(AnimeService animeService, SearchService searchService) {
        this.animeService = animeService;
        this.searchService = searchService;
    }

    public void startUpdate() {
        LOG.warn("Anime update started");
        SearchObject lastSearchAnime = searchService.getSearchObjectByUserId(-1);

        AnimeVostParser animeVostParser = new AnimeVostParser();
        try {
            animeVostParser.getUpdateFromSite(lastSearchAnime.getName(), lastSearchAnime.getDate());
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOG.info("Reading finshed");

        for (int i = 0; i < animeVostParser.getNewAnime().size(); i++) {
            animeService.insertAnime(animeVostParser.getNewAnime().get(i).getName(),
                    animeVostParser.getNewAnime().get(i).getPublicationDate(), animeVostParser.getNewAnime().get(i).getSite(),
                    animeVostParser.getNewAnime().get(i).getNewSeries());
            LOG.info("Inserted" + animeVostParser.getNewAnime().get(i).getName());

            try {
                int id = animeService.getAnimeIdByNameAndDate(animeVostParser.getNewAnime().get(i).getName(),
                        animeVostParser.getNewAnime().get(i).getPublicationDate());
                animeService.updateSubscribedAnime(id, animeVostParser.getNewAnime().get(i).getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (!animeVostParser.getNewAnime().isEmpty()) {
            int size = animeVostParser.getNewAnime().size() - 1;
            searchService.updateLastSearchObject(-1, animeVostParser.getNewAnime().get(size).getName(),
                    animeVostParser.getNewAnime().get(size).getPublicationDate());
        }
        LOG.warn("Anime update finished. Updated " + animeVostParser.getNewAnime().size() + " record's");
    }

}
