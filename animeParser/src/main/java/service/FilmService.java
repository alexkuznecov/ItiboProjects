package service;

import domain.Film;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

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
}
