package de.schneider21.what2eat.meal.business;

import de.schneider21.what2eat.meal.data.BasicMeal;
import de.schneider21.what2eat.meal.data.ExtendedMeal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MealService implements IMealService {

    private final Logger log = LoggerFactory.getLogger(MealService.class);
    private IMenuService menuService;
    private IWeatherService weatherService;

    @Autowired
    public MealService(IMenuService menuService, IWeatherService weatherService) {
        Objects.requireNonNull(menuService);
        this.menuService = menuService;
        Objects.requireNonNull(weatherService);
        this.weatherService = weatherService;
    }

    @Override
    public List<BasicMeal> getAllAvailableMeals() {
        return menuService.getAllAvailableMeals();
    }

    @Override
    public ExtendedMeal getExtendedMealForDate(String dateString) {
        final BasicMeal meal = menuService.getMealForDate(dateString);
        if (meal == null) {
            return null;
        }
        final Double temperatureInCelsius = weatherService.getTemperatureInCelsius("Kaiserslautern", "DE", dateString);
        if (temperatureInCelsius != null) {
            log.info("Temperature received is {}°C", temperatureInCelsius);
        } else {
            log.info("No temperature value could be found");
        }
        int coldBowlProbabilityInPercent = calculateColdBowlProbabilityInPercent(temperatureInCelsius);
        ExtendedMeal extendedMeal = new ExtendedMeal(meal.getDate(), meal.getTitle(), meal.getPrice(),
                coldBowlProbabilityInPercent);
        return extendedMeal;
    }

    @Override
    public int calculateColdBowlProbabilityInPercent(Double temperature) {
        if (temperature == null) {
            return -1;
        }
        return Math.toIntExact(Math.round(Math.max(0, Math.min(100, (temperature - 20) * 10))));
    }
}
