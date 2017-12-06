package utils;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class StaticContent {
    private StaticContent() {}

    private static final String PREFIX = "/WEB-INF/jsp/";
    private static final String SUFFIX = ".jsp";

    public static final File FILE_DIRECTORY = new File("d:\\work\\JAVA_COURSE\\WebProjectMain\\src\\outer_files\\");
    public static final String FILE_NAME = "unloaded_file";
    public static final String FILE_SUFFIX = ".txt";

    private static final String DATE_TIME_FORMAT_SAVE = "yyyy-MM-dd HH:mm:ss.s";
    private static final String DATE_FORMAT_SAVE = "yyyy-MM-dd";
    private static final String DATE_FORMAT_DISPLAY = "dd.MM.yyyy";
    private static final String DATE_TIME_FORMAT_DISPLAY = "dd.MM.yyyy HH:mm";
    private static final String DATE_TIME_FORMAT_INPUT = "yyyy-MM-dd'T'HH:mm";
    private static final String TIME_FORMAT = "HHmmss";

    public static final DateTimeFormatter dateTimeSaveFormat = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_SAVE, Locale.UK);
    public static final DateTimeFormatter dateSaveFormat = DateTimeFormatter.ofPattern(DATE_FORMAT_SAVE, Locale.UK);

    public static final DateTimeFormatter dateTimeInputFormat = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_INPUT, Locale.UK);

    public static final DateTimeFormatter dateDisplayFormat = DateTimeFormatter.ofPattern(DATE_FORMAT_DISPLAY, Locale.UK);
    public static final DateTimeFormatter dateTimeDisplayFormat = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_DISPLAY, Locale.UK);

    public static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern(TIME_FORMAT, Locale.UK);

    public static String createViewPath(String viewName) {
        return String.format("%s%s%s", PREFIX, viewName, SUFFIX);
    }
}
