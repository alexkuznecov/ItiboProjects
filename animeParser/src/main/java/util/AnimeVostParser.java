package util;

import domain.Anime;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnimeVostParser {

    private static final Logger LOG = LoggerFactory.getLogger(AnimeVostParser.class);

    private List<Anime> newAnime;

    private String siteUrl;

    private String baseUrl;

    public AnimeVostParser () {
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

            char chr = '[';
            String str = String.valueOf(chr);

            Connection con = Jsoup.connect(siteUrl).userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21");
            con.timeout(180000).ignoreHttpErrors(true).followRedirects(true);
            Document doc = con.get();
            Element el = doc.getElementById("dle-content");
            List<Element> elements = el.getElementsByClass("shortstory");

            for (int i = 0; i < elements.size(); i++) {
                Anime parsedAnime = new Anime();

                List<Element> elements1 = elements.get(i).getElementsByClass("shortstoryHead");
                List<Element> elements2 = elements.get(i).getElementsByClass("staticInfoLeftData");

                String bufNameCompl = elements1.get(0).text();
                List<Element> elementsForName = elements1.get(0).getElementsByTag("a");
                String bufDateCompl = elements2.get(0).text();

                String[] list = bufDateCompl.split(" ");
                int monthNumber = DateConvertor.getMonthNumberByName(list[1]);
                parsedAnime.setPublicationDate(list[0] + "-" + monthNumber + "-" + list[2]);

                String[] list1 = bufNameCompl.split("\\[");
                parsedAnime.setName(list1[0]);
                String[] list2 = list1[1].split(" ");

                if (list2[0].contains("-")) {
                    String[] list3 = list2[0].split("-");
                    parsedAnime.setNewSeries(list3[1]);
                } else {
                    parsedAnime.setNewSeries(list2[0]);
                }

                String hrefAttr = elementsForName.get(0).attr("href");
                parsedAnime.setSite(hrefAttr);
                LOG.info(parsedAnime.getName() + " " + parsedAnime.getNewSeries() + " " + parsedAnime.getPublicationDate());

                if (parsedAnime.getName().equals(name) && parsedAnime.getPublicationDate().equals(lastUpdateDate)) {
                    endSearch = true;
                    break;
                } else {
                    newAnime.add(parsedAnime);
                }
            }

        }

    }

}
