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
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class PlaceService {
    public ResponseEntity<String> places() throws IOException {
        log.info("Place method called");

        String url = "https://api.iticket.az/az/v5/events?client=web";

        Connection connection = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (HTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3").ignoreContentType(true);

        Document document = connection.get();
        JSONObject jsonResponse = new JSONObject(document.text());
        JSONArray venuesArray = jsonResponse.getJSONObject("response").getJSONArray("venues");

        List<JSONObject> venueDetailsList = StreamSupport.stream(venuesArray.spliterator(), true).map(obj -> (JSONObject) obj).map(venue -> {
            JSONObject result = new JSONObject();
            result.put("id", venue.getInt("id"));
            result.put("name", venue.getString("name"));
            return result;
        }).collect(Collectors.toList());

        JSONArray filteredVenuesArray = new JSONArray(venueDetailsList);
        return ResponseEntity.ok(filteredVenuesArray.toString(2).replace("\n", System.lineSeparator()));
    }


    public ResponseEntity<String> map(Long venueId) throws IOException {
        log.info("Method called to get details for venue with id: {}", venueId);

        String url = "https://api.iticket.az/az/v5/events?client=web";

        Connection connection = Jsoup.connect(url)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (HTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
                .ignoreContentType(true);

        Document document = connection.get();
        JSONObject jsonResponse = new JSONObject(document.text());
        JSONArray venuesArray = jsonResponse.getJSONObject("response").getJSONArray("venues");

        List<JSONObject> venueDetailsList = StreamSupport.stream(venuesArray.spliterator(), true)
                .map(obj -> (JSONObject) obj)
                .filter(v -> v.getInt("id") == venueId)
                .map(venue -> {
                    JSONObject result = new JSONObject();
                    result.put("map_lat", venue.getString("map_lat"));
                    result.put("map_lng", venue.getString("map_lng"));
                    return result;
                })
                .collect(Collectors.toList());

        if (venueDetailsList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        JSONArray filteredVenuesArray = new JSONArray(venueDetailsList);
        return ResponseEntity.ok(filteredVenuesArray.toString(2).replace("\n", System.lineSeparator()));
    }

    public ResponseEntity<String> phone(Long venueId) throws IOException {
        log.info("Method called to get details for phone with id: {}", venueId);

        String url = "https://api.iticket.az/az/v5/events?client=web";

        Connection connection = Jsoup.connect(url)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (HTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
                .ignoreContentType(true);

        Document document = connection.get();
        JSONObject jsonResponse = new JSONObject(document.text());
        JSONArray venuesArray = jsonResponse.getJSONObject("response").getJSONArray("venues");

        List<JSONObject> venueDetailsList = StreamSupport.stream(venuesArray.spliterator(), true)
                .map(obj -> (JSONObject) obj)
                .filter(v -> v.getInt("id") == venueId)
                .map(venue -> {
                    JSONObject result = new JSONObject();
                    if (venue.isNull("phone")) {
                        result.put("phone", JSONObject.NULL);
                    } else {
                        result.put("phone", venue.getString("phone"));
                    }
                    return result;
                })
                .collect(Collectors.toList());

        if (venueDetailsList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        JSONArray filteredVenuesArray = new JSONArray(venueDetailsList);
        return ResponseEntity.ok(filteredVenuesArray.toString(2).replace("\n", System.lineSeparator()));
    }

    public ResponseEntity<String> mobile(Long venueId) throws IOException {
        log.info("Method called to get details for mobile with id: {}", venueId);

        String url = "https://api.iticket.az/az/v5/events?client=web";

        Connection connection = Jsoup.connect(url)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (HTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
                .ignoreContentType(true);

        Document document = connection.get();
        JSONObject jsonResponse = new JSONObject(document.text());
        JSONArray venuesArray = jsonResponse.getJSONObject("response").getJSONArray("venues");

        List<JSONObject> venueDetailsList = StreamSupport.stream(venuesArray.spliterator(), true)
                .map(obj -> (JSONObject) obj)
                .filter(v -> v.getInt("id") == venueId)
                .map(venue -> {
                    JSONObject result = new JSONObject();
                    if (venue.isNull("mobile")) {
                        result.put("mobile", JSONObject.NULL);
                    } else {
                        result.put("mobile", venue.getString("mobile"));
                    }
                    return result;
                })
                .collect(Collectors.toList());

        if (venueDetailsList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        JSONArray filteredVenuesArray = new JSONArray(venueDetailsList);
        return ResponseEntity.ok(filteredVenuesArray.toString(2).replace("\n", System.lineSeparator()));
    }
}
