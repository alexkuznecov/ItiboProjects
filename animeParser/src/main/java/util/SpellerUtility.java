package util;

import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpellerUtility {

    public static List<String> getCheckedResult(String userInput) throws IOException {
        List<String> result = new ArrayList<String>();
        result.add(userInput);
        String url = "http://speller.yandex.net/services/spellservice/checkText";
        Connection connection = HttpConnection.connect(url).ignoreHttpErrors(true);
        Connection.Response response = connection.execute();

        Connection postData = connection.url(url)
                .cookies(response.cookies())
                .ignoreHttpErrors(true)
                .data("text", userInput)
                .method(Connection.Method.GET)
                .followRedirects(true);
        Connection.Response response1 = postData.execute();

        Document doc = response1.parse();
        List<Element> elem = doc.getElementsByTag("error");
        for (int i = 0; i < elem.size(); i++) {
            Element wordElem = elem.get(i).getElementsByTag("word").first();
            String replacment = wordElem.text();
            Element sElem = elem.get(i).getElementsByTag("s").first();
            String newValue = sElem.text();
            userInput = userInput.replace(replacment, newValue);
        }
        result.add(userInput);
        return result;
    }

}
