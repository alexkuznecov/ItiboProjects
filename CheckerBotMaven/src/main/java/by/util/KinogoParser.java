package by.util;

import by.domain.Anime;
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

    private List<Anime> newAnime;

    private String siteUrl;

    private String baseUrl;

    public KinogoParser() {
        newAnime = new ArrayList<Anime>();
    }

    public void setNewAnime(List<Anime> newAnime) {
        this.newAnime = newAnime;
    }

    public List<Anime> getNewAnime() {
        return newAnime;
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
        siteUrl = "http://animevost.org";
        baseUrl = "http://animevost.org";
    }

    public void getUpdateFromSite(String name, String lastUpdateDate) throws IOException {

        newAnime = new ArrayList<Anime>();

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
            Document doc = con.get();
            String str1 = doc.outerHtml();
            //LOG.info(str1);
            Pattern pattern = Pattern.compile("shortstory.*?<h2>.*?href=\"(.*?)\">(.*?)\\[((\\d*-(\\d*).*?)|(\\d*).*?)<.*?<\\/span.*?<span.*?>((\\d*).([а-яА-Я]*).(\\d{0,4}))<\\/span>", Pattern.DOTALL | Pattern.MULTILINE);
            Matcher matcher = pattern.matcher(str1);

            while (matcher.find()) {
                Anime parsedAnime = new Anime();
                parsedAnime.setSite(matcher.group(1));
                parsedAnime.setName(matcher.group(2));
                if (matcher.group(6) == null) {
                    parsedAnime.setNewSeries(matcher.group(5));
                } else {
                    parsedAnime.setNewSeries(matcher.group(6));
                }
                String date = matcher.group(8) + "-" + DateConvertor.getMonthNumberByName(matcher.group(9)) + "-" + matcher.group(10);
                parsedAnime.setPublicationDate(date);
                LOG.info(parsedAnime.getName() + " " + parsedAnime.getNewSeries() + " " + parsedAnime.getPublicationDate());

                if (parsedAnime.getName().equals(name) && parsedAnime.getPublicationDate().equals(lastUpdateDate)) {
                    endSearch = true;
                    Collections.reverse(newAnime);
                    break;
                } else {
                    newAnime.add(parsedAnime);
                }
            }
        }
    }

}
