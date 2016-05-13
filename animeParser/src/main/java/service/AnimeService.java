package service;

import domain.Anime;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface AnimeService {

    @Results(value = {
            @Result(id = true, property = "id", column = "anime_id"),
            @Result(property = "name", column = "anime_name"),
            @Result(property = "publicationDate", column = "anime_publicationDate"),
            @Result(property = "site", column = "anime_site")
    })
    @Select("select * from anime where anime_id =#{id}")
    Anime getAnimeById(@Param(value = "id") Integer id);

    @Select("select anime_id from anime where anime_name = #{name}")
    Integer getAnimeIdByName(@Param(value = "name") String name);

    @Insert("insert into anime(anime_name, anime_publicationDate, anime_site , user_id) values (#{name}, #{publicationDate}, #{site} ,#{user_id})")
    void insertAnime(@Param(value = "name") String name, @Param(value = "publicationDate") String publicationDate, @Param(value = "site") String site, @Param(value = "user_id") Integer user_id);

    @Results(value = {
            @Result(id = true, property = "id", column = "anime_id"),
            @Result(property = "name", column = "anime_name"),
            @Result(property = "publicationDate", column = "anime_publicationDate"),
            @Result(property = "site", column = "anime_site"),
            @Result(property = "newSeries", column = "anime_newSeries")
    })
    @Select("select * from updatedAnime where anime_id > #{beginId}")
    List<Anime> getAnimeByBeginId(@Param(value = "beginId") Integer id);

    @Select("select anime_id from updatedAnime order by anime_id desc limit 1")
    Integer getLastAnimeId();

    @Select("select id from anime_users where user_id = #{userId} and anime_name = #{animeName}")
    Integer getRelationIdIfSubscribed(@Param(value = "userId") Integer userId, @Param(value = "animeName") String animeName);

    @Insert("insert into anime_users(anime_name, user_id) values (#{animeName}, #{userId})")
    void subscribeUser(@Param(value = "userId") Integer userId, @Param(value = "animeName") String animeName);
}
