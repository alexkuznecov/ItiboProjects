package by.util;

import by.domain.Film;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KinogoParser {

    private static final Logger LOG = LoggerFactory.getLogger(KinogoParser.class);

    private List<Film> newFilm;

    private String siteUrl;

    private String baseUrl;

    public KinogoParser() {
        newFilm = new ArrayList<Film>();
    }

    public void setNewFilm(List<Film> newFilm) {
        this.newFilm = newFilm;
    }

    public List<Film> getNewFilm() {
        return newFilm;
    }

    private void setSiteUrl() {
//        FileInputStream fileInputStream;
//        Properties property = new Properties();
//
//        try {
//            fileInputStream = new FileInputStream("src/main/resources/sites.properties");
//            property.load(fileInputStream);
//            fileInputStream.close();
//            siteUrl = property.getProperty("animevost");
//        } catch (IOException e) {
//
//        }
        siteUrl = "http://kinogo.co/";
        baseUrl = "http://kinogo.co/";
    }

    public void getUpdateFromSite(String name) throws IOException {

        newFilm = new ArrayList<Film>();

        Boolean firstPage = true;
        Boolean endSearch = false;
        int pageViewsCounter = 1;

        while (endSearch != true) {

            if (firstPage == true) {
                setSiteUrl();
                pageViewsCounter++;
                firstPage = false;
            } else {
                siteUrl = baseUrl + "/page/" + pageViewsCounter + "/";
                pageViewsCounter++;
            }

            try {
                Thread.sleep(3000);
                LOG.info("Slipped in 3000");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Connection con = Jsoup.connect(siteUrl).userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21");
            con.timeout(180000).ignoreHttpErrors(true).followRedirects(true);
            try {
                Document doc = con.get();
                String str1 = doc.outerHtml();
                //LOG.info(str1);
                Pattern pattern = Pattern.compile("shortstory.*\"zagolovki\"><a href=\"(.*?)\">(.*?)\\((.*?)\\).*?Качество:<\\/b>.(.*?)<.*Перевод:<\\/b>.(.*?)<", Pattern.DOTALL | Pattern.MULTILINE);
                Matcher matcher = pattern.matcher(str1);

                while (matcher.find()) {
                    Film parsedFilm = new Film();
                    parsedFilm.setSite(matcher.group(1));
                    StringBuffer filmName = new StringBuffer(matcher.group(2));
                    filmName.setLength(filmName.length() - 1);
                    parsedFilm.setName(filmName.toString());
                    parsedFilm.setYear(Integer.parseInt(matcher.group(3)));
                    parsedFilm.setQuality(matcher.group(4).replace("-","").toUpperCase().trim());
                    parsedFilm.setSound(matcher.group(5).trim());

                    if (parsedFilm.getName().equals(name)) {
                        endSearch = true;
                        Collections.reverse(newFilm);
                        break;
                    } else {
                        newFilm.add(parsedFilm);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
