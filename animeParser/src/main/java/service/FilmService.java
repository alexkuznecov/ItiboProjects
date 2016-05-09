package service;

import domain.Film;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface FilmService {

    @Results(value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "year", column = "year"),
            @Result(property = "videoQuality", column = "quality"),
            @Result(property = "sound", column = "sound"),
            @Result(property = "site", column = "site")
    })
    @Select("select * from updatedFilm where id > #{beginId}")
    List<Film> getFilmByBeginId(@Param(value = "beginId") Integer id);

    @Results(value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name")
    })
    @Select("select id,name from updatedFilm where name like #{pattern} limit 10")
    List<Film> getFilmByPattern(@Param(value = "pattern") String pattern);

    @Select("select id from film_users where film_id = #{filmId} and user_id = #{userId}")
    Integer getRelationIdIfSubscribed(@Param(value = "userId") Integer userId, @Param(value = "filmId") Integer filmId);

    @Insert("insert into film_users(user_id, film_id) values (#{userId}, #{filmId})")
    void subscribeUser(@Param(value = "userId") Integer userId, @Param(value = "filmId") Integer filmId);
}