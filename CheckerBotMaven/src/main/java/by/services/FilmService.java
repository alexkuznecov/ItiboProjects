package by.services;

import by.domain.Film;
import by.domain.FilmTemplate;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface FilmService {

    @Insert("insert into updatedFilm(name, year, quality , sound, site) values (#{name}, #{year}, #{quality} ,#{sound}, #{site})")
    void insertFilm(@Param(value = "name") String name, @Param(value = "year") Integer year, @Param(value = "quality") String quality,
                    @Param(value = "sound") String sound, @Param(value = "site")String site);

    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "year", column = "year"),
            @Result(property = "quality", column = "quality"),
            @Result(property = "sound", column = "sound"),
            @Result(property = "site", column = "site")
    })
    @Select("select id, name, year, quality, sound, site from updatedFilm where name = #{name} and year = #{year}")
    Film getFilmByNameAndYear(@Param(value = "name") String name, @Param(value = "year") Integer year);

    @Update("update updatedFilm SET name=#{name}, year=#{year}, quality=#{quality}, sound=#{sound}, site=#{site} where id = #{id}")
    void updateFilmById(@Param(value = "name") String name, @Param(value = "year") Integer year, @Param(value = "quality") String quality,
                                @Param(value = "sound") String sound, @Param(value = "site")String site, @Param(value = "id") Integer id);

    @Results(value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "filmName", column = "film_name"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "found", column = "found")
    })
    @Select("select * from filmForSearch_user where found = 0")
    List<FilmTemplate> getFilmForSearch();

    @Update("update filmForSearch_user set found=1 where id = #{id}")
    void updateSearchFilmFound(@Param(value = "id") Integer id);
}
