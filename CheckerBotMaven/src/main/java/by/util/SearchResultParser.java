package by.util;

import by.domain.Film;
import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchResultParser {

    private static final Logger LOG = LoggerFactory.getLogger(SearchResultParser.class);

    private List<Film> foundedFilms;

    public SearchResultParser() {
        this.foundedFilms = new ArrayList<Film>();
    }

    public void searchFilmForName(String searchedFilmName) throws IOException {

        String htmlPage = getPage(searchedFilmName);

        Pattern pattern = Pattern.compile("zagolovki\"><a href=\"(.*?)\">(.*?)\\((.*?)\\).*?Качество:<\\/b>.(.*?)<.*?Перевод:<\\/b>.(.*?)<", Pattern.DOTALL | Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(htmlPage);
        while (matcher.find()) {
            Film parsedFilm = new Film();
            parsedFilm.setSite(matcher.group(1));
            StringBuffer filmName = new StringBuffer(matcher.group(2));
            filmName.setLength(filmName.length() - 1);
            parsedFilm.setName(filmName.toString());
            try {
                parsedFilm.setYear(Integer.parseInt(matcher.group(3)));
            } catch (NumberFormatException e) {
                parsedFilm.setYear(2016);
            }
            parsedFilm.setQuality(matcher.group(4).replace("-", "").toUpperCase().trim());
            parsedFilm.setSound(matcher.group(5).trim());

            LOG.info(parsedFilm.getName() + " " + parsedFilm.getYear() + " " + parsedFilm.getSite() + " " + parsedFilm.getQuality() + " " + parsedFilm.getSound());
            foundedFilms.add(parsedFilm);
        }

        if (foundedFilms.size() != 0) {

        }
    }

    public String getPage(String filmName) throws IOException {

        String url = "http://kinogo.co/";
        String userAgent = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:44.0) Gecko/20100101 Firefox/44.0";
        String headerlang = "en-US,en;q=0.5";
        String headerType = "application/x-www-form-urlencoded";
        String paramDo = "search";
        String subaction = "search";
        String search_start = "1";
        String full_search = "0";
        String result_from = "1";
        String story = URLEncoder.encode(filmName, "CP1251");

        Connection connection = HttpConnection.connect(url).ignoreHttpErrors(true).userAgent(userAgent);
        Connection.Response response = connection.execute();

        Connection postData = connection.url(url)
                    .cookies(response.cookies())
                    .ignoreHttpErrors(true)
                    .userAgent(userAgent)
                    .header("Content-Language", headerlang)
                    .header("Content-Type", headerType)
                    .data("do",paramDo)   //true
                    .data("subaction", subaction)
                    .data("search_start", search_start)
                    .data("full_search", full_search)
                    .data("result_from", result_from)
                    .data("story", story)
                    .method(Connection.Method.POST)
                    .followRedirects(true);

        Connection.Response response1 = postData.execute();

        Document doc = response1.parse();
        return doc.html();
    }

    public List<Film> getFoundedFilms() {
        return foundedFilms;
    }

    public void setFoundedFilms(List<Film> foundedFilms) {
        this.foundedFilms = foundedFilms;
    }
}
