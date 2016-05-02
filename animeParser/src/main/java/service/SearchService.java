package service;


import domain.LastUpdate;
import domain.SearchObject;
import org.apache.ibatis.annotations.*;

public interface SearchService {

      @Results(value = {
              @Result(id = true, property = "id", column = "resultId"),
              @Result(property = "name", column = "resultName"),
              @Result(property = "date", column = "resultDate"),
              @Result(property = "userId", column = "userId")
      })
      @Select("select * from searchResult where userId = #{userId}")
      SearchObject getSearchObjectByUserId(@Param(value = "userId") Integer userId);

      @Update("update searchResult SET resultName=#{name}, resultDate=#{publicationDate} where userId = #{userId}")
      void updateLastSearchObject(@Param(value = "userId") Integer userId, @Param(value = "name") String animeName,
                                    @Param(value = "date") String animeDate);


      @Results(value = {
              @Result(id = true, property = "id", column = "id"),
              @Result(property = "userId", column = "userId"),
              @Result(property = "animeId", column = "animeId"),
              @Result(property = "filmId", column = "filmId")
      })
      @Select("select * from lastUpdateId where userId = #{userId}")
      LastUpdate getLastUpdatesIdByUserId(@Param(value = "userId") Integer userId);


      @Update("update lastUpdateId SET animeId = #{animeId}, filmId = #{filmId} where userId = #{userId}")
      void updateLastUpdates(@Param(value = "userId") Integer userId, @Param(value = "animeId") Integer animeId,
                                    @Param(value = "filmId") Integer filmId);

}
