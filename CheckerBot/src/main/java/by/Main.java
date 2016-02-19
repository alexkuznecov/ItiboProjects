package by;

import by.domain.Anime;
import by.services.AnimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        AnimeService animeService = (AnimeService) ctx.getBean("animeService");

        Anime anime = animeService.getAnimeById(19);

        LOG.info("Anime id = 5 : " + anime.getName() + " " + anime.getPublicationDate() + " " + anime.getSite());
    }
}
