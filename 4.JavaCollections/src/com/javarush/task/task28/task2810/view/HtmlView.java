package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.List;

public class HtmlView implements View {
    private Controller controller;
    private final String filePath = "./4.JavaCollections/src/" +
                                        this.getClass().getPackage().getName().replace('.', '/') +
                                        "/vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            String updatedFileContent = getUpdatedFileContent(vacancies);
            updateFile(updatedFileContent);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }

    private String getUpdatedFileContent(List<Vacancy> list) {
        Document document = null;
        try {
            document = getDocument();
            Element element = document.getElementsByClass("template").first();

            Element copyElement = element.clone();
            copyElement.removeAttr("style");
            copyElement.removeClass("template");

            for (Element e:
                 document.getElementsByAttributeValue("class", "vacancy")) {
                e.remove();
            }

            for (Vacancy v :
                    list) {
                Element outerHtml = copyElement.clone();

                outerHtml.getElementsByClass("city").first().text(v.getCity());
                outerHtml.getElementsByClass("companyName").first().text(v.getCompanyName());
                outerHtml.getElementsByClass("salary").first().text(v.getSalary());

                Element eTmp = outerHtml.getElementsByTag("a").first();
                eTmp.text(v.getTitle());
                eTmp.attr("href", v.getUrl());

                element.before(outerHtml);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Some exception occurred";
        }
        return document.toString();
    }

    private void updateFile(String s) {
        try {
            FileWriter fileWriter = new FileWriter(new File(filePath));
            fileWriter.write(s);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }
}
