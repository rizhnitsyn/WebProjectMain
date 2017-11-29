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
        ForecastDao.getInstance().addForecast(new Forecast(forecast.getFirstTemResult(), forecast.getSecondTeamResult(), forecast.getUserId(),
                new Match(forecast.getMatchId())));
    }
}
