package de.schneider21.what2eat;

import de.schneider21.what2eat.meal.business.*;

/**
 * Service Factory. Central entry point for getting the service implementations.
 * Modeled as singleton. Use {@link ServiceFactory#getInstance()} to access the (single) instance.
 */
public class ServiceFactory {

    private static volatile ServiceFactory INSTANCE;

    public static ServiceFactory getInstance() {
        // double check idiom
        if (INSTANCE == null) {
            synchronized (ServiceFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ServiceFactory();
                }
            }
        }
        return INSTANCE;
    }

    private IMenuService menuService;
    private IMealService mealService;
    private IWeatherService weatherService;

    private ServiceFactory() {
        int thirtyMinutesInMs = 30 * 60 * 60 * 1000;
        menuService = new CachingMenuService(new MensaKlService(), thirtyMinutesInMs);
        weatherService = new CachingWeatherService(new WeatherBitService(), thirtyMinutesInMs);
        mealService = new MealService(menuService, weatherService);
    }


    public IMealService getMealService() {
        return mealService;
    }

    public IMenuService getMenuService() {
        return menuService;
    }

    public IWeatherService getWeatherService() {
        return weatherService;
    }
}
