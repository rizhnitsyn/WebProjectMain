package utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class StaticContent {
    private StaticContent() {}

    public static final String jspPath = "/WEB-INF/jsp";
    public static final String URL = "jdbc:mysql://localhost:3306/forecasts?useSSL=false";
    public static final String USER = "root";
    public static final String PASSWORD = "кщще";
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.UK);
    public static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm", Locale.UK);
}
