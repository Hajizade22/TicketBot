package az.ematrix.ticketbot.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class MoneyService {

    public String min(int min) throws IOException {
        log.info("Min method called with min: {}", min);

        String url = "https://api.iticket.az/az/v5/events?client=web";

        Connection connection = Jsoup.connect(url)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
                .ignoreContentType(true);

        Document document = connection.get();
        JSONObject jsonResponse = new JSONObject(document.text());
        JSONArray minArray = jsonResponse.getJSONObject("response").getJSONObject("events").getJSONArray("data");

        List<JSONObject> filteredMinimum = StreamSupport.stream(minArray.spliterator(), true)
                .map(obj -> (JSONObject) obj)
                .filter(minimum -> minimum.getInt("min_price") >= min)
//                .map(event -> {
//                    JSONObject result = new JSONObject();
//                    result.put("poster_bg_url", event.getString("poster_bg_url"));
//                    result.put("category_slug", event.getString("category_slug"));
//                    return result;
//                })
                .collect(Collectors.toList());

        JSONArray filteredMinimumPrice = new JSONArray(filteredMinimum);
        return filteredMinimumPrice.toString(2).replace("\n", System.lineSeparator());
    }

    public String max(int max) throws IOException {
        log.info("Max method called with max: {}", max);

        String url = "https://api.iticket.az/az/v5/events?client=web";

        Connection connection = Jsoup.connect(url)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
                .ignoreContentType(true);

        Document document = connection.get();
        JSONObject jsonResponse = new JSONObject(document.text());
        JSONArray maxArray = jsonResponse.getJSONObject("response").getJSONObject("events").getJSONArray("data");

        List<JSONObject> filteredMaxPrice = StreamSupport.stream(maxArray.spliterator(), true)
                .map(obj -> (JSONObject) obj)
                .filter(maximum -> maximum.getInt("max_price") <= max)
//                .map(event -> {
//                    JSONObject result = new JSONObject();
//                    result.put("poster_bg_url", event.getString("poster_bg_url"));
//                    result.put("category_slug", event.getString("category_slug"));
//                    return result;
//                })
                .collect(Collectors.toList());

        JSONArray maxPrice = new JSONArray(filteredMaxPrice);
        return maxPrice.toString(2).replace("\n", System.lineSeparator());
    }

    public String price(int min, int max) throws IOException {
        log.info("Price method called with min and max: {}", min, max);

        String url = "https://api.iticket.az/az/v5/events?client=web";

        Connection connection = Jsoup.connect(url)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
                .ignoreContentType(true);

        Document document = connection.get();
        JSONObject jsonResponse = new JSONObject(document.text());
        JSONArray priceArray = jsonResponse.getJSONObject("response").getJSONObject("events").getJSONArray("data");

        List<JSONObject> filteredPrice = StreamSupport.stream(priceArray.spliterator(), true)
                .map(obj -> (JSONObject) obj)
                .filter(minPrice -> minPrice.getInt("min_price") >= min)
                .filter(maxPrice -> maxPrice.getInt("max_price") <= max)
                .map(event -> {
                    JSONObject result = new JSONObject();
                    result.put("poster_bg_url", event.getString("poster_bg_url"));
                    result.put("category_slug", event.getString("category_slug"));
                    return result;
                })
                .collect(Collectors.toList());

        JSONArray filteredPriceArray = new JSONArray(filteredPrice);
        return filteredPriceArray.toString(2).replace("\n", System.lineSeparator());
    }

    public String findMaxPrice() throws IOException {
        log.info("MaxPrice method called to find the maximum price");

        String url = "https://api.iticket.az/az/v5/events?client=web";

        Connection connection = Jsoup.connect(url)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
                .ignoreContentType(true);

        Document document = connection.get();
        JSONObject jsonResponse = new JSONObject(document.text());
        JSONArray maximum = jsonResponse.getJSONObject("response").getJSONObject("events").getJSONArray("data");

        OptionalInt maxPrice = StreamSupport.stream(maximum.spliterator(), true)
                .map(obj -> (JSONObject) obj)
                .mapToInt(event -> event.getInt("max_price"))
                .max();

        if (maxPrice.isPresent()) {
            int max = maxPrice.getAsInt();

            List<JSONObject> maxPriceFind = StreamSupport.stream(maximum.spliterator(), true)
                    .map(obj -> (JSONObject) obj)
                    .filter(maximumPrice -> maximumPrice.getInt("max_price") == max)
                    .collect(Collectors.toList());

            JSONArray maximumPriceArray = new JSONArray(maxPriceFind);
            return maximumPriceArray.toString(2).replace("\n", System.lineSeparator());
        } else {
            return "No events found";
        }
    }
}
