package by.services;


import by.domain.SearchObject;
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

      @Update("update searchResult SET resultName=#{name}, resultDate=#{date} where userId = #{userId}")
      void updateLastSearchObject(@Param(value = "userId") Integer userId, @Param(value = "name") String animeName,
                                  @Param(value = "date") String animeDate);

}
