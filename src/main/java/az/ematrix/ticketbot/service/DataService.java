package az.ematrix.ticketbot.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
@Slf4j
public class DataService {
    public ResponseEntity<String> start(String startDate) throws IOException {
        log.info("Start method called with startDate: {}", startDate);

        String url = "https://api.iticket.az/az/v5/events?client=web";

        Connection connection = Jsoup.connect(url)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
                .ignoreContentType(true);

        Document document = connection.get();
        JSONObject jsonResponse = new JSONObject(document.text());
        JSONArray dataArray = jsonResponse.getJSONObject("response").getJSONObject("events").getJSONArray("data");

        List<JSONObject> eventsStartAt = StreamSupport.stream(dataArray.spliterator(), true)
                .map(obj -> (JSONObject) obj)
                .filter(start -> start.getString("event_starts_at").compareTo(startDate) >= 0)
                .collect(Collectors.toList());

        JSONArray startArray = new JSONArray(eventsStartAt);
        return ResponseEntity.ok(startArray.toString(2).replace("\n", System.lineSeparator()));
    }

    public ResponseEntity<String> end(String endDate) throws IOException {
        log.info("End method called with endDate: {}", endDate);

        String url = "https://api.iticket.az/az/v5/events?client=web";

        Connection connection = Jsoup.connect(url)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
                .ignoreContentType(true);

        Document document = connection.get();
        JSONObject jsonResponse = new JSONObject(document.text());
        JSONArray dataArray = jsonResponse.getJSONObject("response").getJSONObject("events").getJSONArray("data");

        List<JSONObject> eventsEndsAT = StreamSupport.stream(dataArray.spliterator(), true)
                .map(obj -> (JSONObject) obj)
                .filter(end -> end.getString("event_ends_at").compareTo(endDate) <= 0)
                .collect(Collectors.toList());

        JSONArray endArray = new JSONArray(eventsEndsAT);
        return ResponseEntity.ok(endArray.toString(2).replace("\n", System.lineSeparator()));
    }

    public ResponseEntity<String> startAndEnd(String startDate, String endDate) throws IOException {
        log.info("StartAndEnd method called with startDate: {} and endDate: {}", startDate, endDate);

        String url = "https://api.iticket.az/az/v5/events?client=web";

        Connection connection = Jsoup.connect(url)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
                .ignoreContentType(true);

        Document document = connection.get();
        JSONObject jsonResponse = new JSONObject(document.text());
        JSONArray startAndEndArray = jsonResponse.getJSONObject("response").getJSONObject("events").getJSONArray("data");

        List<JSONObject> startAndEnd = StreamSupport.stream(startAndEndArray.spliterator(), true)
                .map(obj -> (JSONObject) obj)
                .filter(start -> start.getString("event_starts_at").compareTo(startDate) >= 0)
                .filter(end -> end.getString("event_ends_at").compareTo(endDate) <= 0)
                .map(event -> {
                    JSONObject result = new JSONObject();
                    result.put("poster_bg_url", event.getString("poster_bg_url"));
                    result.put("category_slug", event.getString("category_slug"));
                    return result;
                })
                .collect(Collectors.toList());

        JSONArray startEndArray = new JSONArray(startAndEnd);
        return ResponseEntity.ok(startEndArray.toString(2).replace("\n", System.lineSeparator()));
    }

    public ResponseEntity<String> startWithLocalDate() throws IOException {
        log.info("StartWithLocalDate method called");

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentLocalTime = localDateTime.format(formatter);

        String url = "https://api.iticket.az/az/v5/events?client=web";

        Connection connection = Jsoup.connect(url)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
                .ignoreContentType(true);

        Document document = connection.get();
        JSONObject jsonResponse = new JSONObject(document.text());
        JSONArray localArray = jsonResponse.getJSONObject("response").getJSONObject("events").getJSONArray("data");

        List<JSONObject> filteredEvents = StreamSupport.stream(localArray.spliterator(), true)
                .map(obj -> (JSONObject) obj)
                .filter(event -> event.getString("event_starts_at").compareTo(currentLocalTime) >= 0)
                .collect(Collectors.toList());

        JSONArray localDateArray = new JSONArray(filteredEvents);
        return ResponseEntity.ok(localDateArray.toString(2).replace("\n", System.lineSeparator()));
    }
}





