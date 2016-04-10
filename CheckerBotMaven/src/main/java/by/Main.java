package by;

import by.services.AnimeService;
import by.services.FilmService;
import by.services.SearchService;
import by.util.FilmUpdateStarter;
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
        FilmService filmService = (FilmService) ctx.getBean("filmService");
        while (true) {
//            AnimeUpdateStarter animeUpdateStarter = new AnimeUpdateStarter(animeService, searchService);
//            animeUpdateStarter.startUpdate();

            FilmUpdateStarter filmUpdateStarter = new FilmUpdateStarter(filmService, searchService);
            filmUpdateStarter.startUpdate();


            try {
                Thread.sleep(3600000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
