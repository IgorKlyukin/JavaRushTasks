package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        Document document;
        List<Vacancy> list = new ArrayList<>();
        for (int i = 0;; i++) {
            try {
                document = getDocument(searchString, i);
                Elements elements = document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
                if (!elements.isEmpty()) {
                    Vacancy v;
                    for (Element e :
                            elements) {
                        v = new Vacancy();
                        v.setUrl(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").attr("href"));
                        v.setTitle(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").text());
                        v.setCity(e.getElementsByClass("searchresult__address").text());
                        v.setCompanyName(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text());
                        v.setSalary(e.getElementsByClass("b-vacancy-list-salary").text());
                        v.setSiteName("hh.ua");
                        list.add(v);
                    }
                } else
                    break;

            } catch (IOException e) {}
        }

        return list;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        Connection connection = Jsoup.connect(String.format(URL_FORMAT, searchString, page));
        connection.userAgent("Mozilla/5.0 (Windows NT 10.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36 OPR/58.0.3135.132");
        connection.referrer("no-referrer-when-downgrade");
        return connection.get();
    }
}
