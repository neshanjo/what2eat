package de.schneider21.what2eat.meal.business;

import org.junit.jupiter.api.Test;

/**
 * This is an example how one can increase test coverage drastically without any actual benefit since the test is
 * useless (no assert used, and the weather service even logs an error!)
 */
public class IncreaseCoverageDummyTest {

    @Test
    public void test() {
        new MensaKlService().getAllAvailableMeals();
        new WeatherBitService().getTemperatureInCelsius(null, null, null);
    }
}
