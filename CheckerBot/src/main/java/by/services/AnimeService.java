package by.services;

import by.domain.Anime;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface AnimeService {

    @Results(value = {
            @Result(id = true, property = "id", column = "anime_id"),
            @Result(property = "name", column = "anime_name"),
            @Result(property = "publicationDate", column = "anime_publicationDate"),
            @Result(property = "site", column = "anime_site")
    })
    @Select("select * from anime where anime_id =#{id}")
    Anime getAnimeById(@Param(value = "id") Integer id);


}