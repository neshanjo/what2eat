package de.schneider21.what2eat.meal.business;

import de.schneider21.what2eat.meal.data.BasicMeal;
import de.schneider21.what2eat.meal.data.ExtendedMeal;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This test shows how one can test legacy code that has hardwired depedencies. However, this is something
 * that should be avoided by using depedency injection!
 */
class MealServiceHackedWithReflectionTest {

    static final BasicMeal BASIC_MEAL = new BasicMeal("2021-11-11", "Schneckengulasch", new BigDecimal("3.33"));

    // Here, inheritance is only used to make the service compatible with the type of MealService#menuService
    static class MockMensaKlService extends MensaKlService {
        @Override
        public BasicMeal getMealForDate(String date) {
            return BASIC_MEAL;
        }
    }

    // Here, inheritance is only used to make the service compatible with the type of MealService#weatherService
    static class MockWeatherService extends WeatherBitService {

        private double temperature;

        public MockWeatherService(double temperature) {
            this.temperature = temperature;
        }

        @Override
        public Double getTemperatureInCelsius(String cityName, String countryCode, String dateString) {
            return temperature;
        }
    }

    @Test
    void testGetExtendedMealForDate() throws Exception {
        MealService mealService = new MealService();

        // Injecting the mock services via reflection
        // Note that this is highly depending on the implementation and will break, if
        // the attributes will be renamed.
        Field menuServiceField = MealService.class.getDeclaredField("menuService");
        menuServiceField.setAccessible(true);

        Field weatherServiceField = MealService.class.getDeclaredField("weatherService");
        weatherServiceField.setAccessible(true);

        menuServiceField.set(mealService, new MockMensaKlService());
        weatherServiceField.set(mealService, new MockWeatherService(21.5));

        // Now we can call the mealService method and our mock services will be used
        ExtendedMeal extendedMeal = mealService.getExtendedMealForDate(BASIC_MEAL.getDate());

        assertEquals(BASIC_MEAL.getDate(), extendedMeal.getDate());
        assertEquals(BASIC_MEAL.getTitle(), extendedMeal.getTitle());
        assertEquals(BASIC_MEAL.getPrice(), extendedMeal.getPrice());
        // 21.5°C means 15% cold bowl probability
        assertEquals(15, extendedMeal.getColdBowlProbabilityInPercent());
    }
}