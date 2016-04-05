package by.impl;

import by.domain.SearchObject;
import by.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "searchService")
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
