package de.schneider21.what2eat.meal.business;

import de.schneider21.what2eat.meal.data.BasicMeal;
import de.schneider21.what2eat.meal.data.ExtendedMeal;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This test shows how to do a module test of MealService using simple mock implementations for
 * IMenuService and IWeatherService.
 */
class MealServiceSimpleMockTest {

    static final BasicMeal BASIC_MEAL = new BasicMeal("2021-11-11", "Schneckengulasch", new BigDecimal("3.33"));

    static class MockMensaKlService implements IMenuService {

        @Override
        public List<BasicMeal> getAllAvailableMeals() {
            throw new UnsupportedOperationException("Not implemented in this mock");
        }

        @Override
        public BasicMeal getMealForDate(String date) {
            return BASIC_MEAL;
        }
    }

    static class MockWeatherService implements IWeatherService {

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
    void testGetExtendedMealForDate() {
        // No "hacks" are needed here - we can just inject our mock services via the constructor
        MealService mealService = new MealService(new MockMensaKlService(), new MockWeatherService(21.5));

        // When we now call the mealService method, our mock services will be used
        ExtendedMeal extendedMeal = mealService.getExtendedMealForDate(BASIC_MEAL.getDate());

        assertEquals(BASIC_MEAL.getDate(), extendedMeal.getDate());
        assertEquals(BASIC_MEAL.getTitle(), extendedMeal.getTitle());
        assertEquals(BASIC_MEAL.getPrice(), extendedMeal.getPrice());
        // 21.5°C means 15% cold bowl probability
        assertEquals(15, extendedMeal.getColdBowlProbabilityInPercent());
    }
}