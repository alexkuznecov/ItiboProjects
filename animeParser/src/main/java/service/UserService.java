package service;

import domain.Anime;
import domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserService {

    @Insert("insert into users(name,surname,email,login,password) values (#{name}, #{surname}, #{email}, #{login}, #{password})")
    void insertUser(@Param(value = "name") String name, @Param(value = "surname") String surname, @Param(value = "email") String email,
                    @Param(value = "login") String login, @Param(value = "password") String password);

    @Results(value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "surname", column = "surname"),
            @Result(property = "email", column = "email"),
            @Result(property = "login", column = "login")
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

    @Insert("insert into lastUpdateId(userId, animeId, filmId) values (#{userId}, #{animeId}, #{filmId})")
    void setLastUpdateId(@Param(value = "userId") Integer userId, @Param(value = "animeId") Integer animeId,
                         @Param(value = "filmId") Integer filmId);

    @Select("select id from users where login = #{login}")
    Integer getUserIdByLogin(@Param(value = "login") String login);
}
