package de.schneider21.what2eat.meal.business;

import de.schneider21.what2eat.meal.data.DataWithTime;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service that caches method calls to the given IWeatherService instance.
 * Only weather requests for Kaiserslautern are cached.
 * This class implements a simple cache that grows over time and is never reset.
 * It is safe to use with threads, however, in the case that two calls happen exactly at
 * the same time, the cache is updated twice.
 */
public class CachingWeatherService implements IWeatherService {

    private IWeatherService service;
    private long cachingTimeMs;

    public CachingWeatherService(IWeatherService service, long cachingTimeMs) {
        this.service = service;
        this.cachingTimeMs = cachingTimeMs;
    }

    private Map<String, DataWithTime<Double>> temperatureInKlWithTimeByDate = new ConcurrentHashMap<>();

    @Override
    public Double getTemperatureInCelsius(String cityName, String countryCode, String dateString) {
        if (cityName.equalsIgnoreCase("Kaiserslautern") && countryCode.equalsIgnoreCase("de")) {
            long currentTime = System.currentTimeMillis();
            DataWithTime<Double> temperatureInKlWithTime = temperatureInKlWithTimeByDate.get(dateString);
            if (temperatureInKlWithTime == null || temperatureInKlWithTime.getData() == null
                    || currentTime - temperatureInKlWithTime.getTime() > cachingTimeMs) {
                System.out.println("CachingWeatherService: Updating cache");
                Double temperature = service.getTemperatureInCelsius(cityName, countryCode, dateString);
                temperatureInKlWithTime = new DataWithTime<>(temperature, currentTime);
                temperatureInKlWithTimeByDate.put(dateString, temperatureInKlWithTime);
            } else {
                System.out.println("CachingWeatherService: Using cached result");
            }
            return temperatureInKlWithTime.getData();
        }
        // only caching for Kaiserslautern. Otherwise, the service is called without caching:
        return service.getTemperatureInCelsius(cityName, countryCode, dateString);

    }
}
