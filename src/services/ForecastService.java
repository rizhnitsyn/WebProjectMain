package services;

import DAO.ForecastDao;
import entities.Forecast;

public final class ForecastService {
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
        ForecastDao.getInstance().addForecast(forecast);
    }
}
