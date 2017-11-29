package utils;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class StaticContent {
    private StaticContent() {}

    private static final String PREFIX = "/WEB-INF/jsp/";
    private static final String SUFFIX = ".jsp";
//    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.UK);
//    public static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm", Locale.UK);
    private static final String DateTimeFormatPattern = "dd.MM.yyyy hh:mm";
    private static final String DateFormatPattern = "dd.MM.yyyy";
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DateTimeFormatPattern, Locale.UK);
    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DateFormatPattern, Locale.UK);

    public static String createViewPath(String viewName) {
        return String.format("%s%s%s", PREFIX, viewName, SUFFIX);
    }
}
