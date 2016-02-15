package service;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SubscriberService {

    @Select("select id from anime_users where anime_id = #{anime_id} and user_id = #{user_id}")
    Integer getSubscribeIdByUserIdAndAnimeId(@Param(value = "user_id") Integer user_id, @Param(value = "anime_id") Integer anime_id);

    @Insert("insert into anime_users(anime_id, user_id) values (#{anime_id}, #{user_id})")
    void insertSubscriber(@Param(value = "user_id") Integer user_id, @Param(value = "anime_id") Integer anime_id);

}
