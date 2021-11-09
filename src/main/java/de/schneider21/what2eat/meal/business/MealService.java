package de.schneider21.what2eat.meal.business;

import de.schneider21.what2eat.meal.data.BasicMeal;
import de.schneider21.what2eat.meal.data.ExtendedMeal;

import java.util.List;

public class MealService {

    private MensaKlService menuService;
    private WeatherBitService weatherService;

    public MealService() {
        this.menuService = new MensaKlService();
        this.weatherService = new WeatherBitService();
    }

    public List<BasicMeal> getAllAvailableMeals() {
        return menuService.getAllAvailableMeals();
    }

    public ExtendedMeal getExtendedMealForDate(String dateString) {
        final BasicMeal meal = menuService.getMealForDate(dateString);
        if (meal == null) {
            return null;
        }
        final Double temperatureInCelsius = weatherService.getTemperatureInCelsius("Kaiserslautern", "DE", dateString);
        if (temperatureInCelsius != null) {
            System.out.printf("MealService: Temperature received is %.2f°C\n", temperatureInCelsius);
        } else {
            System.out.printf("MealService: No temperature value could be found\n");
        }
        int coldBowlProbabilityInPercent = calculateColdBowlProbabilityInPercent(temperatureInCelsius);
        ExtendedMeal extendedMeal = new ExtendedMeal(meal.getDate(), meal.getTitle(), meal.getPrice(),
                coldBowlProbabilityInPercent);
        return extendedMeal;
    }

    public int calculateColdBowlProbabilityInPercent(Double temperature) {
        if (temperature == null) {
            return -1;
        }
        return Math.toIntExact(Math.round(Math.max(0, Math.min(100, (temperature - 20) * 10))));
    }
}
