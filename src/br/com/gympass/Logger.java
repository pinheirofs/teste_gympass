package br.com.gympass;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Classe responsavel por funcionar como wapper do sistema de log.
 */
public class Logger {

    private static final String WARN = "WARN";
    private static final String SEPARATOR = " - ";
    private final String name;

    public Logger(final String name) {
        this.name = name;
    }

    public void warn(final String msg) {
        System.out.println(createLine(WARN, msg));
    }

    private String createLine(final String type, final String msg) {
        final StringBuilder builder = new StringBuilder();
        builder.append(name);
        builder.append(SEPARATOR);

        builder.append(type);
        builder.append(SEPARATOR);

        final Calendar calendar = GregorianCalendar.getInstance();
        final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");
        builder.append(format.format(calendar.getTime()));
        builder.append(SEPARATOR);

        builder.append(msg);

        return builder.toString();
    }

}
