package br.com.gympass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe responsavel por separar as informações da linha.
 */
public class LineSplitter {

    private static final int INFORMATION_SIZE = 11;
    private static final String REGEX = "([0-9]{2}):([0-9]{2}):([0-9]{2})\\.([0-9]{1,3})[\\p{Space}]+([0-9]+)[\\p{Space}\\–]+([A-Z\\.a-z]+)[\\p{Space}]+([0-9]+)[\\p{Space}]+([0-9]+):([0-9]{2})\\.([0-9]{1,3})[\\p{Space}]+([0-9]+,[0-9]+)";
    private static final Logger LOGGER = new Logger("LineSplitter");

    private String line;
    private boolean valid;
    private int hours;
    private int minutes;
    private int seconds;
    private int miliseconds;
    private int pilotCode;
    private String pilotName;
    private int lapNumber;
    private int lapMinute;
    private int lapSeconds;
    private int lapMilisecond;
    private float averageSpeed;

    public LineSplitter() {
    }

    public void setLine(final String line) {
        this.line = line;
    }

    /**
     * Separa as informacoes contidas na linha informada.
     */
    public void split() {
        if (line == null || line.isEmpty()) {
            valid = false;
            return;
        } else {
            valid = true;
        }

        final Pattern pattern = Pattern.compile(REGEX);
        final Matcher informations = pattern.matcher(line);
        if (informations.groupCount() != INFORMATION_SIZE) {
            LOGGER.warn("Erro ao parsear a linnha, tamanho diferente de " + INFORMATION_SIZE + ", tamanho obtido == "
                    + informations.groupCount());
            valid = false;
            return;
        }

        informations.find();
        convertHours(informations.group(1));
        convertMinutes(informations.group(2));
        convertSeconds(informations.group(3));
        convertMiliseconds(informations.group(4));
        convertPilotCode(informations.group(5));
        convertPilotName(informations.group(6));
        convertLapNumber(informations.group(7));
        convertLapMinute(informations.group(8));
        convertLapSegundo(informations.group(9));
        convertLapMiliseconds(informations.group(10));
        convertAvaregeSpeed(informations.group(11));
    }

    private void convertAvaregeSpeed(final String avaregeSpeedString) {
        try {
            averageSpeed = Float.valueOf(avaregeSpeedString.replace(',', '.'));
        } catch (final NumberFormatException e) {
            LOGGER.warn("Parser do media de velocidade da volta invalidos.");
            valid = false;
        }
    }

    private void convertLapMiliseconds(final String lapMilisecondString) {
        try {
            lapMilisecond = Integer.valueOf(lapMilisecondString);
        } catch (final NumberFormatException e) {
            LOGGER.warn("Parser do milisegundo da volta invalidos.");
            valid = false;
        }
    }

    private void convertLapSegundo(final String lapSecondsString) {
        try {
            lapSeconds = Integer.valueOf(lapSecondsString);
        } catch (final NumberFormatException e) {
            LOGGER.warn("Parser do segundos da volta invalidos.");
            valid = false;
        }
    }

    private void convertLapMinute(final String lapMinuteString) {
        try {
            lapMinute = Integer.valueOf(lapMinuteString);
        } catch (final NumberFormatException e) {
            LOGGER.warn("Parser do minuto da volta invalidos.");
            valid = false;
        }
    }

    private void convertLapNumber(final String lapNumberString) {
        try {
            lapNumber = Integer.valueOf(lapNumberString);
        } catch (final NumberFormatException e) {
            LOGGER.warn("Parser do numero da volta invalidos.");
            valid = false;
        }
    }

    private void convertPilotName(final String pilotName) {
        this.pilotName = pilotName;
    }

    private void convertPilotCode(final String pilotCodeString) {
        try {
            pilotCode = Integer.valueOf(pilotCodeString);
        } catch (final NumberFormatException e) {
            LOGGER.warn("Parser do codigo do piloto invalidos.");
            valid = false;
        }
    }

    private void convertMiliseconds(final String milisecondsString) {
        try {
            miliseconds = Integer.valueOf(milisecondsString);
        } catch (final NumberFormatException e) {
            LOGGER.warn("Parser dos milisegundos invalidos.");
            valid = false;
        }
    }

    private void convertSeconds(final String secondsString) {
        try {
            seconds = Integer.valueOf(secondsString);
        } catch (final NumberFormatException e) {
            LOGGER.warn("Parser dos segundos invalidos.");
            valid = false;
        }
    }

    private void convertMinutes(final String minutesString) {
        try {
            minutes = Integer.valueOf(minutesString);
        } catch (final NumberFormatException e) {
            LOGGER.warn("Parser dos minutos invalidos.");
            valid = false;
        }
    }

    private void convertHours(final String hoursString) {
        try {
            hours = Integer.valueOf(hoursString);
        } catch (final NumberFormatException e) {
            LOGGER.warn("Parser das horas invalidos.");
            valid = false;
        }
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMiliseconds() {
        return miliseconds;
    }

    public int getPilotCode() {
        return pilotCode;
    }

    public String getPilotName() {
        return pilotName;
    }

    public int getLapNumber() {
        return lapNumber;
    }

    public int getLapMinute() {
        return lapMinute;
    }

    public int getLapSeconds() {
        return lapSeconds;
    }

    public int getLapMilisecond() {
        return lapMilisecond;
    }

    public float getAverageSpeed() {
        return averageSpeed;
    }

    public boolean isValid() {
        return valid;
    }

}
