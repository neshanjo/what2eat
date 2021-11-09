package de.schneider21.what2eat.meal.data;

import java.math.BigDecimal;

public class BasicMeal {

    // For a real system, it would be more robust to use Date or long instead of string here
    private String date;
    private String title;
    private BigDecimal price;

    public BasicMeal(String date, String title, BigDecimal price) {
        this.date = date;
        this.title = title;
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BasicMeal{" +
                "date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
