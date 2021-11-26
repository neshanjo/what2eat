package de.schneider21.what2eat.meal.business;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.schneider21.what2eat.meal.data.BasicMeal;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MensaKlService implements IMenuService {

    private static final String API_URL = "http://www.mensa-kl.de/api.php";

    /**
     * This is the format as returned by the mensa-kl API
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class LunchEntry {
        //{
        //	"m_id": "43345",
        //	"date": "2019-01-21",
        //	"loc": "1",
        //	"title": "Rahmbraten  mit Brasilsoße, Pommes frites und Salat",
        //	"price": "4.05",
        //	"rating": "3.9",
        //	"rating_amt": "10",
        //	"image": "md4h4ju8a9lz6wx.jpg",
        //	"icon": "pork"
        //}

        private String date, loc, title, price, rating;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getLoc() {
            return loc;
        }

        public void setLoc(String loc) {
            this.loc = loc;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }
    }

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public List<BasicMeal> getAllAvailableMeals() {

        HttpUrl.Builder urlBuilder = HttpUrl
                .parse(API_URL)
                .newBuilder()
                .addQueryParameter("date", "all")
                .addQueryParameter("loc", "2") // only requesting menus from "Ausgabe 2"
                .addQueryParameter("format", "json");
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            return parseListResponse(bodyOrEmptyArrayIfError(response))
                    .stream()
                    .map(lunchEntry -> convertToMeal(lunchEntry))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("MealService: Exception when listing all lunch entries");
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public BasicMeal getMealForDate(String date) {

        HttpUrl.Builder urlBuilder = HttpUrl
                .parse(API_URL)
                .newBuilder()
                .addQueryParameter("date", date)
                .addQueryParameter("loc", "2") // only requesting menus from "Ausgabe 2"
                .addQueryParameter("format", "json");
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final List<LunchEntry> meals = parseListResponse(bodyOrEmptyArrayIfError(response));
            if (meals.isEmpty()) {
                return null;
            }
            return convertToMeal(meals.get(0));
        } catch (IOException e) {
            System.out.println("MealService: Exception when listing meals for " + date);
            e.printStackTrace();
            return null;
        }
    }

    private String bodyOrEmptyArrayIfError(Response response) throws IOException {
        final String body = response.body().string();
        if (!body.startsWith("-1")) {
            return body;
        }
        System.out.println("MensaKlService: no results found");
        return "[]";
    }

    private List<LunchEntry> parseListResponse(String body) throws IOException {
        return new ObjectMapper().reader()
                .forType(mapper.getTypeFactory().constructCollectionType(List.class, LunchEntry.class))
                .readValue(body.replace("\t", " "));
    }

    private BasicMeal convertToMeal(LunchEntry lunchEntry) {
        BasicMeal meal = new BasicMeal(lunchEntry.getDate(), lunchEntry.getTitle(),
                new BigDecimal(lunchEntry.getPrice()));
        return meal;
    }
}
