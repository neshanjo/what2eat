package de.schneider21.what2eat.meal.data;

import java.math.BigDecimal;

public class ExtendedMeal extends BasicMeal {

    private int coldBowlProbabilityInPercent;

    public ExtendedMeal(String date, String title, BigDecimal price, int coldBowlProbabilityInPercent) {
        super(date, title, price);
        this.coldBowlProbabilityInPercent = coldBowlProbabilityInPercent;
    }

    /**
     * returns the probability that a cold bowl is served with the meal. A return value of -1 means that the probability
     * could not be calculated.
     *
     * @return
     */
    public int getColdBowlProbabilityInPercent() {
        return coldBowlProbabilityInPercent;
    }

    public void setColdBowlProbabilityInPercent(int coldBowlProbabilityInPercent) {
        this.coldBowlProbabilityInPercent = coldBowlProbabilityInPercent;
    }

    @Override
    public String toString() {
        return "ExtendedMeal{" +
                "date='" + getDate() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", price=" + getTitle() +
                ", coldBowlProbabilityInPercent=" + coldBowlProbabilityInPercent +
                '}';
    }
}
