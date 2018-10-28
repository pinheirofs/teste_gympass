package br.com.gympass;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class RacingReport {

    private static final Object TITLES_REPORT = "Posição Chegada   Código Piloto   Nome Piloto   Qtde Voltas Completadas   Tempo Total\n";
    private List<Pilot> pilots;
    private StringBuilder builder;
    private String report;

    public RacingReport() {
    }

    public void setPilots(final List<Pilot> pilots) {
        this.pilots = pilots;
    }

    public void createReport() {
        builder = new StringBuilder();
        builder.append(TITLES_REPORT);

        for (final Pilot pilot : pilots) {
            format(pilot);
        }

        report = builder.toString();
    }

    private void format(final Pilot pilot) {
        formatFinishingPosition(pilot);
        formatCode(pilot);
        formatName(pilot);
        formatLastLap(pilot);
        formatTotalTime(pilot);

        builder.append('\n');
    }

    private void formatTotalTime(final Pilot pilot) {
        final Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTimeInMillis(pilot.getTotalRacingTime());
        final Date date = calendar.getTime();

        final SimpleDateFormat format = new SimpleDateFormat("mm:ss.SSS");
        final String totalTimeString = format.format(date);

        builder.append(totalTimeString);

        for (int i = totalTimeString.length(); i < 11; ++i) {
            builder.append(' ');
        }
    }

    private void formatLastLap(final Pilot pilot) {
        final String lastLapString = Integer.toString(pilot.getLastLap());
        builder.append(lastLapString);

        for (int i = lastLapString.length(); i < 26; ++i) {
            builder.append(' ');
        }
    }

    private void formatName(final Pilot pilot) {
        final String name = pilot.getName();
        builder.append(name);

        for (int i = name.length(); i < 14; ++i) {
            builder.append(' ');
        }
    }

    private void formatCode(final Pilot pilot) {
        final String codeString = Integer.toString(pilot.getCode());
        builder.append(codeString);

        for (int i = codeString.length(); i < 16; ++i) {
            builder.append(' ');
        }
    }

    private void formatFinishingPosition(final Pilot pilot) {
        final String finishingPositionString = Integer.toString(pilot.getFinishingPosition());
        builder.append(finishingPositionString);

        for (int i = finishingPositionString.length(); i < 18; ++i) {
            builder.append(' ');
        }
    }

    public String getReport() {
        return report;
    }

}
