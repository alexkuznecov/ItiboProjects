package by.services;

import by.domain.AnimeReportBean;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;


public interface AnimeService {

    @Insert("insert into updatedAnime(anime_name, anime_publicationDate, anime_site , anime_newSeries) values (#{name}, #{publicationDate}, #{site} ,#{newSeries})")
    void insertAnime(@Param(value = "name") String name, @Param(value = "publicationDate") String publicationDate, @Param(value = "site") String site, @Param(value = "newSeries") String newSeries);

    @Results(value = {
            @Result(property = "anime_name", column = "anime_name"),
            @Result(property = "anime_date", column = "anime_publicationDate"),
            @Result(property = "anime_newSeries", column = "anime_newSeries")
    })
    @Select("select anime_name, anime_publicationDate, anime_newSeries from updatedAnime order by updatedAnime.anime_id desc limit 30")
    ArrayList<AnimeReportBean> getAnimeForReport();

    @Select("select anime_id from updatedAnime where anime_name = #{name} and anime_publicationDate = #{date}")
    Integer getAnimeIdByNameAndDate(@Param(value = "name") String name, @Param(value = "date") String date);

    @Update("update anime_users set updated = 1, updatedId = #{animeId} where anime_name = #{animeName}")
    void updateSubscribedAnime(@Param(value = "animeId") Integer animeId, @Param(value = "animeName") String animeName);
}