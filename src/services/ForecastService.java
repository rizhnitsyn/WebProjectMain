package services;

import DAO.daoImplementation.ForecastDaoImpl;
import entities.Forecast;

public class ForecastService {
    private static ForecastService INSTANCE;

    private ForecastService() {}

    public static ForecastService getInstance() {
        if (INSTANCE == null) {
            synchronized (ForecastService.class) {
                if (INSTANCE == null) {
                    INSTANCE =  new ForecastService();
                }
            }
        }
        return INSTANCE;
    }

    public void addForecast(Forecast forecast) {
        ForecastDaoImpl.getInstance().addForecast(forecast);
    }
}
