package az.ematrix.ticketbot.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class EventsService {
    public String events() throws IOException {
        log.info("Events method called");

        String url = "https://api.iticket.az/az/v5/categories?client=web";

        Connection connection = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (HTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3").ignoreContentType(true);

        Document document = connection.get();
        JSONObject jsonResponse = new JSONObject(document.text());
        JSONArray categoriesArray = jsonResponse.getJSONArray("response");

        Set<String> uniqueSlugs = new HashSet<>();
        JSONArray uniqueSlugsArray = new JSONArray();

        for (Object obj : categoriesArray) {
            JSONObject category = (JSONObject) obj;
            String slug = category.getString("slug");

            if (uniqueSlugs.add(slug)) {
                JSONObject uniqueSlugObject = new JSONObject();
                uniqueSlugObject.put("category", slug);
                uniqueSlugsArray.put(uniqueSlugObject);
            }
        }

        return uniqueSlugsArray.toString(2).replace("\n", System.lineSeparator());
    }

    public String processEvents(String categoryInput) throws IOException {
        log.info("ProcessEvents method called with categoryInput: {}", categoryInput);

        String url = "https://api.iticket.az/az/v5/events?client=web&category_slug="+categoryInput;

        Connection connection = Jsoup.connect(url)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (HTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
                .ignoreContentType(true);

        Document document = connection.get();
        JSONObject jsonResponse = new JSONObject(document.text());
        JSONObject eventData = jsonResponse.getJSONObject("response").getJSONObject("events");

        List<JSONObject> uniqueCategorySlugsList = new ArrayList<>();

        JSONArray eventsArray = eventData.getJSONArray("data");

        for (Object obj : eventsArray) {
            JSONObject event = (JSONObject) obj;
            String categorySlug = event.getString("category_slug");

            if (categorySlug.equals(categoryInput)) {
                JSONObject uniqueCategorySlugObject = new JSONObject();
                uniqueCategorySlugObject.put("category_slug", categorySlug);


                if (event.has("venues") && !event.getJSONArray("venues").isEmpty()) {
                    JSONObject venue = event.getJSONArray("venues").getJSONObject(0);
                    uniqueCategorySlugObject.put("venue_name", venue.getString("name"));
                }

                uniqueCategorySlugsList.add(uniqueCategorySlugObject);
            }
        }

        return new JSONArray(uniqueCategorySlugsList).toString(2).replace("\n", System.lineSeparator());
    }

    public String ageLimit(String categoryInput) throws IOException {
        log.info("ProcessEvents method called with categoryInput: {}", categoryInput);

        String url = "https://api.iticket.az/az/v5/events?client=web&category_slug="+categoryInput;

        Connection connection = Jsoup.connect(url)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (HTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
                .ignoreContentType(true);

        Document document = connection.get();
        JSONObject jsonResponse = new JSONObject(document.text());
        JSONObject eventData = jsonResponse.getJSONObject("response").getJSONObject("events");

        List<JSONObject> uniqueCategorySlugsList = new ArrayList<>();

        JSONArray eventsArray = eventData.getJSONArray("data");

        for (Object obj : eventsArray) {
            JSONObject event = (JSONObject) obj;
            String categorySlug = event.getString("category_slug");

            if (categorySlug.equals(categoryInput)) {
                JSONObject uniqueCategorySlugObject = new JSONObject();



                if (event.has("venues") && !event.getJSONArray("venues").isEmpty()) {
                    JSONObject venue = event.getJSONArray("venues").getJSONObject(0);
                    uniqueCategorySlugObject.put("venue_name", venue.getString("name"));
                }
                uniqueCategorySlugObject.put("age_limit", event.getString("age_limit"));
                uniqueCategorySlugsList.add(uniqueCategorySlugObject);
            }
        }

        return new JSONArray(uniqueCategorySlugsList).toString(2).replace("\n", System.lineSeparator());
    }

    public String where(String categoryInput) throws IOException {
        log.info("ProcessEvents method called with categoryInput: {}", categoryInput);

        String url = "https://api.iticket.az/az/v5/events?client=web&category_slug="+categoryInput;

        Connection connection = Jsoup.connect(url)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (HTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
                .ignoreContentType(true);

        Document document = connection.get();
        JSONObject jsonResponse = new JSONObject(document.text());
        JSONObject eventData = jsonResponse.getJSONObject("response").getJSONObject("events");

        List<JSONObject> uniqueCategorySlugsList = new ArrayList<>();

        JSONArray eventsArray = eventData.getJSONArray("data");

        for (Object obj : eventsArray) {
            JSONObject event = (JSONObject) obj;
            String categorySlug = event.getString("category_slug");

            if (categorySlug.equals(categoryInput)) {
                JSONObject uniqueCategorySlugObject = new JSONObject();



                if (event.has("venues") && !event.getJSONArray("venues").isEmpty()) {
                    JSONObject venue = event.getJSONArray("venues").getJSONObject(0);
                    uniqueCategorySlugObject.put("venue_name", venue.getString("name"));
                }
                uniqueCategorySlugObject.put("age_limit", event.getString("age_limit"));
                uniqueCategorySlugObject.put("slug", event.getString("slug"));
                uniqueCategorySlugsList.add(uniqueCategorySlugObject);
            }
        }

        return new JSONArray(uniqueCategorySlugsList).toString(2).replace("\n", System.lineSeparator());
    }
}
