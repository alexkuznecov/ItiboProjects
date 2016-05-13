package service;


import domain.Anime;
import domain.Film;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SubscriberService {

    @Results(value = {
            @Result(id = true, property = "id", column = "anime_id"),
            @Result(property = "name", column = "anime_name"),
            @Result(property = "newSeries", column = "anime_newSeries"),
            @Result(property = "publicationDate", column = "anime_publicationDate"),
            @Result(property = "site", column = "anime_site")
    })
    @Select("select updatedAnime.anime_id, updatedAnime.anime_name, updatedAnime.anime_newSeries ,updatedAnime.anime_publicationDate, updatedAnime.anime_site " +
            "   from updatedAnime" +
            "   join anime_users on anime_users.user_id = #{id} " +
            "   and anime_users.updateId = updatedAnime.anime_id")
    List<Anime> getUsersSubscribedAnimeByUserId(@Param(value = "id") Integer id);

    @Results(value = {
            @Result(id = true, property = "id", column = "anime_id"),
            @Result(property = "name", column = "anime_name"),
            @Result(property = "newSeries", column = "anime_newSeries"),
            @Result(property = "publicationDate", column = "anime_publicationDate"),
            @Result(property = "site", column = "anime_site")
    })
    @Select("select updatedAnime.anime_id, updatedAnime.anime_name, updatedAnime.anime_newSeries , updatedAnime.anime_publicationDate, updatedAnime.anime_site " +
            "   from updatedAnime" +
            "   join anime_users on anime_users.user_id = #{id} " +
            "   and anime_users.updateId = updatedAnime.anime_id where anime_users.updated = 1")
    List<Anime> getUsersSubscribedAnimeByUserIdIfUpdated(@Param(value = "id") Integer id);

    @Results(value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "year", column = "year"),
            @Result(property = "videoQuality", column = "quality"),
            @Result(property = "sound", column = "sound"),
            @Result(property = "site", column = "site")
    })
    @Select("select * " +
            "   from updatedFilm" +
            "   join film_users on film_users.user_id = #{id} " +
            "   and film_users.film_id = updatedFilm.id")
    List<Film> getUsersSubscribedFilmByUserId(@Param(value = "id") Integer id);

    @Results(value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "year", column = "year"),
            @Result(property = "videoQuality", column = "quality"),
            @Result(property = "sound", column = "sound"),
            @Result(property = "site", column = "site")
    })
    @Select("select * " +
            "   from updatedFilm" +
            "   join film_users on film_users.user_id = #{id} " +
            "   and film_users.film_id = updatedFilm.id where film_users.updated = 1")
    List<Film> getUsersSubscribedFilmByUserIdIfUpdated(@Param(value = "id") Integer id);

    @Update("update anime_users set updated=0 where user_id = #{userId}")
    void updateReadedAnime(@Param(value = "userId") Integer userId);

    @Update("update film_users set updated=0 where user_id = #{userId}")
    void updateReadedFilm(@Param(value = "userId") Integer userId);

}
