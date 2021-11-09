package de.schneider21.what2eat.meal.business;

public interface IWeatherService {

    Double getTemperatureInCelsius(String cityName, String countryCode, String dateString);
}
