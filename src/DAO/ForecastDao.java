package DAO;

import entities.Forecast;

import java.util.List;

public interface ForecastDao {
    Forecast addForecast(Forecast forecast);
    Forecast updateForecast(Forecast forecast);
    Forecast getForecastById(Long tournamentId);
    List<Forecast> getListOfForecasts();
}
