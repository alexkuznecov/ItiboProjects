package by.services;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;


public interface AnimeService {

    @Insert("insert into updatedAnime(anime_name, anime_publicationDate, anime_site , anime_newSeries) values (#{name}, #{publicationDate}, #{site} ,#{newSeries})")
    void insertAnime(@Param(value = "name") String name, @Param(value = "publicationDate") String publicationDate, @Param(value = "site") String site, @Param(value = "newSeries") String newSeries);




}