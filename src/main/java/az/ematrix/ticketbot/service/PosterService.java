package az.ematrix.ticketbot.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PosterService {
    public  ResponseEntity<?> kidsPosterUrls() {
        List<String> posterUrls = new ArrayList<>();

        String url = "https://api.iticket.az/az/v5/events?client=web&category_slug=kids";

        try {
            Connection connection = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3").ignoreContentType(true);

            Document document = connection.get();

            JSONObject jsonResponse = new JSONObject(document.text());
            JSONObject eventsObject = jsonResponse.getJSONObject("response").getJSONObject("events");
            JSONArray kidsPosterArray = eventsObject.getJSONArray("data");

            for (int i = 0; i < kidsPosterArray.length(); i++) {
                JSONObject eventObject = kidsPosterArray.getJSONObject(i);
                String posterUrl = eventObject.getString("poster_url");
                posterUrls.add(posterUrl);
                log.info("Found poster URL: {}", posterUrl);
            }
        } catch (IOException e) {
            log.error("Error while fetching or parsing the document for kids events", e);
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }

        return ResponseEntity.ok(posterUrls);

    }


    public ResponseEntity<?> concertsPosterUrls() {
        List<String> posterUrls = new ArrayList<>();

        String url = "https://api.iticket.az/az/v5/events?client=web&category_slug=concerts";

        try {
            Connection connection = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3").ignoreContentType(true);

            Document document = connection.get();

            JSONObject jsonResponse = new JSONObject(document.text());
            JSONObject eventsObject = jsonResponse.getJSONObject("response").getJSONObject("events");
            JSONArray concertsPosterArray = eventsObject.getJSONArray("data");

            for (int i = 0; i < concertsPosterArray.length(); i++) {
                JSONObject eventObject = concertsPosterArray.getJSONObject(i);
                String posterUrl = eventObject.getString("poster_url");
                posterUrls.add(posterUrl);
                log.info("Found poster URL: {}", posterUrl);
            }
        } catch (IOException e) {
            log.error("Error while fetching or parsing the document for kids events", e);
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }

        return ResponseEntity.ok(posterUrls);
    }

    public ResponseEntity<?> tourismPosterUrls() {
        List<String> posterUrls = new ArrayList<>();

        String url = "https://api.iticket.az/az/v5/events?client=web&category_slug=tourism";

        try {
            Connection connection = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3").ignoreContentType(true);

            Document document = connection.get();

            JSONObject jsonResponse = new JSONObject(document.text());
            JSONObject eventsObject = jsonResponse.getJSONObject("response").getJSONObject("events");
            JSONArray tourismPosterArray = eventsObject.getJSONArray("data");

            for (int i = 0; i < tourismPosterArray.length(); i++) {
                JSONObject eventObject = tourismPosterArray.getJSONObject(i);
                String posterUrl = eventObject.getString("poster_url");
                posterUrls.add(posterUrl);
                log.info("Found poster URL: {}", posterUrl);
            }
        } catch (IOException e) {
            log.error("Error while fetching or parsing the document for kids events", e);
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }
        return ResponseEntity.ok(posterUrls);
    }

    public ResponseEntity<?> searchPicture(String search) {
        List<String> posterUrls = new ArrayList<>();

        String url = "https://api.iticket.az/az/v5/events?client=web&category_slug=" + search;

        try {
            Connection connection = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3").ignoreContentType(true);

            Document document = connection.get();

            JSONObject jsonResponse = new JSONObject(document.text());
            JSONObject eventsObject = jsonResponse.getJSONObject("response").getJSONObject("events");
            JSONArray eventsArray = eventsObject.getJSONArray("data");

            for (int i = 0; i < eventsArray.length(); i++) {
                JSONObject eventObject = eventsArray.getJSONObject(i);
                String posterUrl = eventObject.getString("poster_url");
                posterUrls.add(posterUrl);
                log.info("Found poster URL: {}", posterUrl);
            }

        } catch (IOException e) {
            log.error("Error while fetching or parsing the document for kids events", e);
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);

        }
        return ResponseEntity.ok(posterUrls);
    }
}

