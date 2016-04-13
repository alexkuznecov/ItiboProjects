package service.impl;

import domain.SearchObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.SearchService;

@Component
public class SearchServiceImpl {

    @Autowired
    SearchService searchService;

    public SearchObject getSearchObjectByUserId(int id) {
        return searchService.getSearchObjectByUserId(id);
    }

    public void updateLastSearchObject(int id, String animeName, String animeDate) {
        searchService.updateLastSearchObject(id,animeName,animeDate);
    }
}
