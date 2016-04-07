package by;

import by.domain.SearchObject;
import by.services.AnimeService;
import by.services.SearchService;
import by.util.KinogoParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        AnimeService animeService = (AnimeService) ctx.getBean("animeService");
        SearchService searchService = (SearchService) ctx.getBean("searchService");

        while (true) {

//            LOG.warn("Anime update started");
//            SearchObject lastSearchAnime = searchService.getSearchObjectByUserId(-1);
//
//            AnimeVostParser animeVostParser = new AnimeVostParser();
//            animeVostParser.getUpdateFromSite(lastSearchAnime.getName(), lastSearchAnime.getDate());
//
//            LOG.info("Reading finshed");
//
//            for (int i =0; i< animeVostParser.getNewAnime().size(); i++) {
//                animeService.insertAnime(animeVostParser.getNewAnime().get(i).getName(), animeVostParser.getNewAnime().get(i).getPublicationDate(), animeVostParser.getNewAnime().get(i).getSite(), animeVostParser.getNewAnime().get(i).getNewSeries());
//                LOG.info("Inserted" + animeVostParser.getNewAnime().get(i).getName());
//            }
//
//            if (!animeVostParser.getNewAnime().isEmpty()) {
//                int size = animeVostParser.getNewAnime().size() - 1;
//                searchService.updateLastSearchObject(-1, animeVostParser.getNewAnime().get(size).getName(), animeVostParser.getNewAnime().get(size).getPublicationDate());
//            }
//            LOG.warn("Anime update finished. Updated " + animeVostParser.getNewAnime().size() + " record's");

            LOG.warn("Film's update started");
            SearchObject lastSearchFilm = searchService.getSearchObjectByUserId(-1);

            KinogoParser kinogoParser = new KinogoParser();
            kinogoParser.getUpdateFromSite(lastSearchFilm.getName());

//            BobfilmParser bobfilmParser = new BobfilmParser();
//            bobfilmParser.getUpdateFromSite(lastSearchFilm.getName());

            LOG.info("Reading finshed");

//            for (int i =0; i< animeVostParser.getNewAnime().size(); i++) {
//                animeService.insertAnime(animeVostParser.getNewAnime().get(i).getName(), animeVostParser.getNewAnime().get(i).getPublicationDate(), animeVostParser.getNewAnime().get(i).getSite(), animeVostParser.getNewAnime().get(i).getNewSeries());
//                LOG.info("Inserted" + animeVostParser.getNewAnime().get(i).getName());
//            }
//
//            if (!animeVostParser.getNewAnime().isEmpty()) {
//                int size = animeVostParser.getNewAnime().size() - 1;
//                searchService.updateLastSearchObject(-1, animeVostParser.getNewAnime().get(size).getName(), animeVostParser.getNewAnime().get(size).getPublicationDate());
//            }
//            LOG.warn("Anime update finished. Updated " + animeVostParser.getNewAnime().size() + " record's");



            try {
                Thread.sleep(3600000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
