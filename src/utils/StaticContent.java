package utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public final class StaticContent {
    private StaticContent() {}

    public static final String PREFIX = "/WEB-INF/jsp/";
    private static final String SUFFIX = ".jsp";
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.UK);
    public static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm", Locale.UK);

    public static String createViewPath(String viewName) {
        return String.format("%s%s%s", PREFIX, viewName, SUFFIX);
    }
}
