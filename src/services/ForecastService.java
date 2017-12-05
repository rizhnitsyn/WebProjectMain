package services;

import DAO.ForecastDao;
import DTO.ForecastAddDto;
import entities.Forecast;
import entities.Match;

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

    public void addForecast(ForecastAddDto forecast) {
        Forecast newForecast = new Forecast(forecast.getFirstTemResult(), forecast.getSecondTeamResult(), forecast.getUserId(), new Match(forecast.getMatchId()));
        ForecastDao forecastDao = ForecastDao.getInstance();
        if (forecastDao.isExist(newForecast)) {
            forecastDao.updateForecast(newForecast);
        } else {
            forecastDao.addForecast(newForecast);
        }
    }
}
