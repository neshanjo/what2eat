package de.schneider21.what2eat.meal.business;

import de.schneider21.what2eat.meal.data.ExtendedMeal;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * This test stub illustrates that is it not possible to perform a module test of
 * MealService that makes sense since it is using hardwired dependencies of
 * MensaKlService and WeatherBitService
 */
class MealServiceTest {

    @Test
    void testGetExtendedMealForDate() {
        MealService mealService = new MealService();
        // Problem 1: Which date should I choose here? If I use a fixed date, in the future,
        // no data will be available anymore. So I choose today's date
        // ExtendedMeal extendedMeal = mealService.getExtendedMealForDate("???");
        String todayDateString = new SimpleDateFormat("YYYY-MM-dd").format(new Date());
        System.out.println(todayDateString);
        ExtendedMeal extendedMeal = mealService.getExtendedMealForDate(todayDateString);

        // Problem 2: Which meal am I going to get here? I do not know, so I cannot test the meal content.

        // Only testing that we get data at all?
        assertNotNull(extendedMeal);
        assertNotNull(extendedMeal.getTitle());
        // Note that these statements still won't work on Saturdays or Sundays (or when the cafeteria is closed)

        // Problem 3: I cannot test the cold bowl probability since I do not know the weather
        //assertEquals(???, extendedMeal.getColdBowlProbabilityInPercent());
    }
}