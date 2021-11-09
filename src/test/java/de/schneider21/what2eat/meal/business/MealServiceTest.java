package de.schneider21.what2eat.meal.business;

import de.schneider21.what2eat.meal.data.BasicMeal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MealServiceTest {

    private IMealService mealService;
    private IWeatherService weatherService;

    private final BasicMeal meal1 = new BasicMeal("2020-03-01", "Kaviar mit Pommes", new BigDecimal("20.00"));
    private final BasicMeal meal2 =
            new BasicMeal("2020-03-02", "Schneckenauflauf mit Fischstäbchen", new BigDecimal("1.10"));
    private final BasicMeal meal3 =
            new BasicMeal("2020-03-03", "Saure Gurken mit roter Grütze", new BigDecimal("2.70"));

    @BeforeEach
    void setUp() {
        weatherService = new IWeatherService() {
            @Override
            public Double getTemperatureInCelsius(String cityName, String countryCode, String dateString) {
                return null;
            }
        };
        mealService = new MealService(
                new MockMenuService(
                        Arrays.asList(meal1, meal2, meal3)
                ), weatherService
        );
    }

    @AfterEach
    void tearDown() {
    }

    private static void assertEqual(BasicMeal expected, BasicMeal actual) {
        if (expected == null) {
            assertNull(actual);
            return;
        }
        // from here: expected is not null
        assertNotNull(actual);
        assertEquals(expected.getDate(), actual.getDate());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getPrice(), actual.getPrice());
    }

    @Test
    void getMealForDate_mealPresent() {
        final BasicMeal expected = meal2;
        final BasicMeal actual = mealService.getExtendedMealForDate("2020-03-02");
        assertEqual(expected, actual);
    }

    @Test
    void getMealForDate_mealNotPresent() {
        final BasicMeal notExisting = mealService.getExtendedMealForDate("2019-03-02");
        assertNull(notExisting);
    }

    @Test
    void calculateColdBowlProbabilityInPercent() {
        assertEquals(-1, mealService.calculateColdBowlProbabilityInPercent(null));
        assertEquals(0, mealService.calculateColdBowlProbabilityInPercent(-1.0));
        assertEquals(0, mealService.calculateColdBowlProbabilityInPercent(0.0));
        assertEquals(0, mealService.calculateColdBowlProbabilityInPercent(19.0));
        assertEquals(0, mealService.calculateColdBowlProbabilityInPercent(19.9));
        assertEquals(0, mealService.calculateColdBowlProbabilityInPercent(20.0));

        assertEquals(10, mealService.calculateColdBowlProbabilityInPercent(21.0));
        assertEquals(47, mealService.calculateColdBowlProbabilityInPercent(24.7));
        assertEquals(50, mealService.calculateColdBowlProbabilityInPercent(25.0));
        assertEquals(99, mealService.calculateColdBowlProbabilityInPercent(29.9));

        assertEquals(100, mealService.calculateColdBowlProbabilityInPercent(30.0));
        assertEquals(100, mealService.calculateColdBowlProbabilityInPercent(30.1));
        assertEquals(100, mealService.calculateColdBowlProbabilityInPercent(40.14));
        assertEquals(100, mealService.calculateColdBowlProbabilityInPercent(100.0));
    }
}