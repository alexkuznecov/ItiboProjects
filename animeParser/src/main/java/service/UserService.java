package service;

import domain.Anime;
import domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserService {

    @Results(value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "surname", column = "surname")
    })
    @Select("select * from users where id =#{id}")
    User getUserById(@Param(value = "id") Integer id);

    @Results(value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "surname", column = "surname")
    })
    @Select("select id,name,surname from users where login = #{login} and password = #{password}")
    User getUserByLoginAndPassword(@Param(value = "login") String login, @Param(value = "password") String password);

    @Results(value = {
            @Result(id = true, property = "id", column = "anime_id"),
            @Result(property = "name", column = "anime_name"),
            @Result(property = "publicationDate", column = "anime_publicationDate")
    })
    @Select("select anime.anime_id, anime.anime_name, anime.anime_publicationDate " +
            "   from anime" +
            "   join anime_users on anime_users.user_id = #{id} " +
            "   and anime_users.anime_id = anime.anime_id")
    List<Anime> getUsersSubscribedAnimeByUserId(@Param(value = "id") Integer id);
}
