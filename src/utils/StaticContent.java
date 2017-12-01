package utils;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class StaticContent {
    private StaticContent() {}

    private static final String PREFIX = "/WEB-INF/jsp/";
    private static final String SUFFIX = ".jsp";
//    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
//    public static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.UK);
    private static final String DateTimeFormatPattern = "yyyy-MM-dd HH:mm:ss.s";
    private static final String DateFormatPattern = "yyyy-MM-dd";
    private static final String DateFormatPatternInput = "dd.MM.yyyy";
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DateTimeFormatPattern, Locale.UK);
    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DateFormatPattern, Locale.UK);
    public static final DateTimeFormatter dateFormatterInput = DateTimeFormatter.ofPattern(DateFormatPatternInput, Locale.UK);

    public static String createViewPath(String viewName) {
        return String.format("%s%s%s", PREFIX, viewName, SUFFIX);
    }
}
