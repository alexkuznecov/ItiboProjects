package by.dao;

import by.domain.AnimeReportBean;
import by.services.AnimeService;

import java.util.ArrayList;

public class AnimeDao {

    public ArrayList<AnimeReportBean> getAnimeReportList(AnimeService animeService) {

        ArrayList<AnimeReportBean> animeReportBeans = new ArrayList<AnimeReportBean>();
        animeReportBeans = animeService.getAnimeForReport();
        return animeReportBeans;
    }

}
