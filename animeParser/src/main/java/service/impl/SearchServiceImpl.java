package service.impl;

import domain.SearchObject;
import service.SearchService;

public class SearchServiceImpl {

    SearchService searchService;

    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }

    public SearchObject getSearchObjectByUserId(int id) {
        return searchService.getSearchObjectByUserId(id);
    }

    public void updateLastSearchObject(int id, String animeName, String animeDate) {
        searchService.updateLastSearchObject(id,animeName,animeDate);
    }
}
