package service;


import domain.Anime;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SubscriberService {

    @Select("select id from anime_users where anime_id = #{anime_id} and user_id = #{user_id}")
    Integer getSubscribeIdByUserIdAndAnimeId(@Param(value = "user_id") Integer user_id, @Param(value = "anime_id") Integer anime_id);

    @Insert("insert into anime_users(anime_id, user_id) values (#{anime_id}, #{user_id})")
    void insertSubscriber(@Param(value = "user_id") Integer user_id, @Param(value = "anime_id") Integer anime_id);

    @Results(value = {
            @Result(id = true, property = "id", column = "anime_id"),
            @Result(property = "name", column = "anime_name"),
            @Result(property = "publicationDate", column = "anime_publicationDate"),
            @Result(property = "site", column = "anime_site")
    })
    @Select("select anime.anime_id, anime.anime_name, anime.anime_publicationDate, anime.anime_site " +
            "   from anime" +
            "   join anime_users on anime_users.user_id = #{id} " +
            "   and anime_users.anime_id = anime.anime_id")
    List<Anime> getUsersSubscribedAnimeByUserId(@Param(value = "id") Integer id);

}