package mainPackage.storage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

public class Storage {
    private HashMap<String, String> rates;

    @Value("${parser.url}")
    private String strURL;

    public Storage() {
        rates = new HashMap<>();
        rates.put("USD", "0");
        rates.put("CNY", "0");
        rates.put("GBR", "0");
        rates.put("JPY", "0");
        updateRates();
    }

    void updateRates() {
        Document doc = null;
        try {
            doc = Jsoup.connect(strURL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements elQuote = doc.getElementsByClass(classNmae);
    }

    public String getRate(String rateName) {
        return rates.get(rateName);
    }
}
