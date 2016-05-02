package service.impl;

import domain.LastUpdate;
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

    public LastUpdate getLastUpdatesIdByUserId(Integer userId) {
        return searchService.getLastUpdatesIdByUserId(userId);
    }

    public void updateLastUpdates(LastUpdate lastUpdate) {
        searchService.updateLastUpdates(lastUpdate.getUserId(), lastUpdate.getAnimeId(), lastUpdate.getFilmId());
    }
}
